// Handler를 통해 WebSocket를 활성화하기 위한 Config

package Discipline.CineHub.config.websocket;

import Discipline.CineHub.config.websocket.CustomHandshakeInterceptor;
import Discipline.CineHub.config.websocket.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;



@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    SocketHandler socketHandler;

    // 요청시 Handler로 route
    // beforeHandshake Method에서 Header 중 필요한 값을 가져오고, true 값 반환하면 Upgrade Header와 함께
    // 101 Switching Protocols 상태 코드를 포함한 Response 반환

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketHandler, "/wss/chat")
                .addInterceptors(new HttpSessionHandshakeInterceptor(), new CustomHandshakeInterceptor())
                .setAllowedOrigins("http://localhost:8080/api/chat"); // localhost, 나중에 배포 시 변경
    }
    // CORS 설정 때문에 origin에서 403 error 발생시
    // String[] origins = {"https://www.url1.com", }등 여러 origin을 setAllowedOrigins에 대입해도 됨

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxBinaryMessageBufferSize(500000);
        container.setMaxTextMessageBufferSize(500000);
        return container;
    }
}
