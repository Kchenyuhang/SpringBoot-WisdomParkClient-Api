package com.niit.soft.client.api.config;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.messaging.simp.config.MessageBrokerRegistry;
        import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
        import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
        import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @ClassName SocketConfig
 * @Description TODO
 * @Author xiaobinggan
 * @Date 2020/6/18 6:59 下午
 * @Version 1.0
 **/
@Configuration
// 消息队列的服务端
@EnableWebSocketMessageBroker
@Slf4j
public class SocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket")
                //跨域处理
                .setAllowedOrigins("*")
                //降级 解决浏览器兼容问题
                .withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/topic/", "/queue/", "/exchange/")
                .setRelayHost("120.26.179.70")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest")
                .setVirtualHost("/");

        // 这句话表示客户向服务器端发送时的主题上面需要加"/app"作为前缀。
        registry.setApplicationDestinationPrefixes("/app");
        // 这句话表示给指定用户发送一对一的主题前缀是"/user"。
        registry.setUserDestinationPrefix("/user");
    }
}
