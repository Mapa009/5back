package kr.co.yooooon.common.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/*
mod_jk 란? 아파치와 톰캣을 연동하기 위한 모듈로써 AJP 프로토콜을 이용하여 아파치에 들어온 요청 중 톰캣이 처리할 요청을 AJP 포트(일반적으로 8009)를 통해 톰캣에 전달하고 그에 대한 응답을 받는 역할을 수행합니다.

Part2. mod_jk를 이용한 *****tomcat 연동(Spring-Boot설정)*******
SpringBoot 1.x와  SpringBoot 2.x의 AJP포트 설정 법에 차이가 있다. 
그 이유는 SpringBoot1.x에서는 내장톰캣을 기본으로 사용하고 있었지만 SpringBoot2.x에서는 내장톰캣대신에 netty를 사용하기 때문이다.

1) application.properties 값추가
2-1) SpringBoot1.x ContainerConfig 클래스 추가
*/

@Configuration
@Data
public class ContainerConfig {

@Value("${tomcat.ajp.port}")
int ajpPort;

@Value("${tomcat.ajp.remoteauthentication}")
String remoteAuthentication;

@Value("${tomcat.ajp.enabled}")
boolean tomcatAjpEnabled;

@Bean
public TomcatServletWebServerFactory servletContainer() {

    TomcatServletWebServerFactory Tomcat = new TomcatServletWebServerFactory();
    if (tomcatAjpEnabled)
    {
        Connector ajpConnector = new Connector("AJP/1.3");
        ajpConnector.setPort(ajpPort);
        ajpConnector.setSecure(false);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        ((AbstractAjpProtocol<?>) ajpConnector.getProtocolHandler()).setSecretRequired(false);
        Tomcat.addAdditionalTomcatConnectors(ajpConnector);
    }

    return Tomcat;
  }

}
