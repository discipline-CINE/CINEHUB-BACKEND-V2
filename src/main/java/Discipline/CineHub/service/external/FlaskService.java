package Discipline.CineHub.service.external;

import Discipline.CineHub.dto.external.FlaskRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class FlaskService {

    private final ObjectMapper objectMapper;

    @Transactional
    public String sendToFlask(FlaskRequestDto dto) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String param = objectMapper.writeValueAsString(dto);

        // dto -> JSON 변환
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(param, headers);
        String url = "http://127.0.0.1:5000/receive_string";

        // Flask 서버로 데이터 전송 후 response return
        return restTemplate.postForObject(url, entity, String.class);


    }
}
