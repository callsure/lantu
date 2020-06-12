package org.lantu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by runshu.lin on 2020/6/6.
 */
@Slf4j
@Component
public class DefaultServerConfig implements ApplicationListener<WebServerInitializedEvent> {

	private int serverPort;

	public int getServerPort() {
		return serverPort;
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		try {
			InetAddress inetAddress = Inet4Address.getLocalHost();
			this.serverPort = event.getWebServer().getPort();
			log.info("项目启动启动成功！接口文档地址: http://"+inetAddress.getHostAddress()+":"+serverPort+"/docs.html");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
