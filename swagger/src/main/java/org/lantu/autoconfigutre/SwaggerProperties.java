package org.lantu.autoconfigutre;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by runshu.lin on 2020/6/11.
 */
@ConfigurationProperties(prefix = "lantu.swagger")
public class SwaggerProperties {

	private boolean enable = false;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
