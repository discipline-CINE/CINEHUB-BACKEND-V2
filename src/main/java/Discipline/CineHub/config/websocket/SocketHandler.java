// 메세지 핸들링

package Discipline.CineHub.config.websocket;

import Discipline.CineHub.domain.ByteArrayMultipartFile;
import Discipline.CineHub.dto.chat.ChatMessageRequestDto;
import Discipline.CineHub.service.file.AwsS3Service;
import Discipline.CineHub.service.chat.ChatMessageService;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class SocketHandler extends TextWebSocketHandler {
    List<HashMap<String, Object>> rls = new ArrayList<>(); // 웹소켓 세션을 담을 리스트 ---roomListSessions
    static int fileUploadIdx = 0;
    static String fileUploadSession = "";

    private final ChatMessageService chatMessageService;
    private final AwsS3Service awsS3Service;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        // 메세지 발송
        String msg = message.getPayload(); // JSON 형식의 String 메시지 받음
        JSONObject obj = jsonToObjectParser(msg); // JSON 데이터를 JSON Object로 parsing

        ChatMessageRequestDto chatMessageRequestDto = ChatMessageRequestDto.builder()
                .userName((String) obj.get("userName"))
                .msg((String) obj.get("msg"))
                .imageUrl(null)
                .roomNumber(Integer.parseInt((String) obj.get("roomNumber")))
                .build();
        chatMessageService.save(chatMessageRequestDto);

        String rN = (String) obj.get("roomNumber"); // 방 번호 받아옴
        String msgType = (String) obj.get("type"); // 메세지 타입 확인
        HashMap<String, Object> temp = new HashMap<String, Object>();

        if (!rls.isEmpty()) {
            for (int i = 0; i < rls.size(); i++) {
                String roomNumber = (String) rls.get(i).get("roomNumber"); // 세션 리스트의 저장된 방 번호 가져옴
                if (roomNumber.equals(rN)) { // 같은 값의 방이 존재하면
                    temp = rls.get(i); // 해당 방 번호의 세션 리스트에 존재하는 모든 object 값을 가져옴
                    fileUploadIdx = i;
                    fileUploadSession = (String) obj.get("sessionId");
                    break;
                }
            }

            if (!msgType.equals("fileUpload")) { // 메시지 타입이 fileUpload가 아닐때만 전송
                // 해당 방의 session만 찾아서 메시지 발송
                for (String k : temp.keySet()) {
                    if (k.equals("roomNumber")) { // 방 번호일 경우에는 건너 뜀
                        continue;
                    }

                    WebSocketSession wss = (WebSocketSession) temp.get(k);
                    if (wss != null) {
                        try {
                            TextMessage textMessage = new TextMessage(obj.toJSONString());
                            wss.sendMessage(textMessage);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        // ByteBuffer 형태의 Payload로 웹소켓 메시지 저장
        ByteBuffer byteBuffer = message.getPayload();
        // byte[]로 변환
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);

        // png도?
        String fileName = "chatting"+ UUID.randomUUID()+".jpg";
        String contentType = "image/jpg";

        ByteArrayMultipartFile multipartFile = new ByteArrayMultipartFile(bytes, contentType, fileName);
        String dirName = "chatting_images";
        URL uploadedFileUrl = awsS3Service.upload(multipartFile, dirName);

        byteBuffer.position(0); // 파일 저장하면서 position값 변경, 0으로 초기화
        // 파일 쓰기 완료되면 이미지 발송

        int roomNumber = (int) session.getAttributes().get("roomNumber");
        String userName = (String) session.getAttributes().get("userName");

        ChatMessageRequestDto chatMessageRequestDto = ChatMessageRequestDto.builder()
                .userName(userName)
                .msg("image")
                .imageUrl(uploadedFileUrl.toString())
                .roomNumber(roomNumber)
                .build();
        chatMessageService.save(chatMessageRequestDto);

        HashMap<String, Object> temp = rls.get(fileUploadIdx);
        for(String k : temp.keySet()) {
            if(k.equals("roomNumber")) {
                continue;
            }
            WebSocketSession wss = (WebSocketSession) temp.get(k);
            try {
                wss.sendMessage(new BinaryMessage(byteBuffer));
                // BinaryMessage 객체를 사용하여 이미지 데이터를 ByteBuffer로 변환 후 각 session에 전송
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 소켓 연결
        super.afterConnectionEstablished(session);
        boolean flag = false;
        String url = session.getUri().toString();
        String roomNumber = url.split("/chatting/")[1];
        int idx = rls.size(); // 방의 사이즈를 조사한다.
        if (rls.size() > 0) {
            for (int i = 0; i < rls.size(); i++) {
                String rN = (String) rls.get(i).get("roomNumber");
                if (rN.equals(roomNumber)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if (flag) { // 존재하는 방이라면 세션만 추가한다.
            HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        } else { // 최초 생성하는 방이라면 방 번호와 세션을 추가
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("roomNumber", roomNumber);
            map.put(session.getId(), session);
            rls.add(map);
        }

        // 세션 등록 끝나면 발급 받은 세션 ID값의 메시지 발송
        JSONObject obj = new JSONObject();
        obj.put("type", "getId");
        obj.put("sessionId", session.getId());
        session.sendMessage(new TextMessage(obj.toJSONString()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 소켓 종료
        log.trace("Socket destroyed");
        if(rls.size() > 0) { // 소켓 종료되면 해당 세션 값 찾아서 지움
            for (int i = 0; i < rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }
}