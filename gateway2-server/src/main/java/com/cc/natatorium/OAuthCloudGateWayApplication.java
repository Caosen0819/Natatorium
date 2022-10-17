package com.cc.natatorium;

import com.cc.natatorium.model.SysParameterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author caosen
 * @data ${DATE}--${TIME}
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableConfigurationProperties(value = {SysParameterConfig.class})
public class OAuthCloudGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(OAuthCloudGateWayApplication.class);
    }
}
