// 웹소켓의 첫 handshake, 연결 확인
// Http Request Header의 connection 속성은 upgrade 여야함
// beforeHandshake는 Client에서 연결 요청이 들어오면 handshake에서 호출
// CustomHandshakeInterceptor는 http에 존재하는 session을 WebSocket session으로 등록

package Discipline.CineHub.config.websocket;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class CustomHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpServletRequest req = servletRequest.getServletRequest();
        HttpSession httpSession = req.getSession();
        String sessionID = httpSession.getId();
        attributes.put("sessionID", sessionID);

        String userName = req.getParameter("userName");

        attributes.put("username", userName);

        return true;
    }

    // WebSocket 연결 후 추가 작업 수행시 사용
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception e) {

    }
}

