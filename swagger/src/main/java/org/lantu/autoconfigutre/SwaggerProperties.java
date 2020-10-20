package org.lantu.autoconfigutre;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by runshu.lin on 2020/6/11.
 */
@ConfigurationProperties(prefix = "lantu.swagger")
@PropertySource(value = {"classpath:application-data.properties"}, encoding = "UTF-8")
public class SwaggerProperties {

	private boolean enable = false;

	private String title;

	private String version = "1.0";

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
