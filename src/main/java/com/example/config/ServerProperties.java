package com.example.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Az on 01/03/2017.
 */
@Component
@ConfigurationProperties(prefix = "properties")
@Getter
@Setter
public class ServerProperties {
    private String name;
    private LocalServer server;

    @Getter
    @Setter
    public static class LocalServer{
        private String host;
        private Integer port;
    }
}
