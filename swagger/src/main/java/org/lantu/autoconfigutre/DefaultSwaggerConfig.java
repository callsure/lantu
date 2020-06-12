package org.lantu.autoconfigutre;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by runshu.lin on 2020/6/6.
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerProperties.class)
public class DefaultSwaggerConfig {

	/**
	 * 可以通过变量设置swagger-ui是否显示，比如测试环境可以暴露api文档，生产环境我们就关闭
	 */
	private final SwaggerProperties swaggerProperties;

	public DefaultSwaggerConfig(SwaggerProperties swaggerProperties) {
		this.swaggerProperties = swaggerProperties;
	}

	@Bean
	public Docket buildDocket() {
//		Predicate<RequestHandler> selector1 = RequestHandlerSelectors.basePackage("com.sw.controller");
//		Predicate<RequestHandler> selector2 = RequestHandlerSelectors.basePackage("com.sw.controller");

		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(buildApiInf())
				.enable(swaggerProperties.isEnable())
				.select()
//				.apis(RequestHandlerSelectors.basePackage(""))// 需要生成文档的包的位置
//				.apis(Predicates.or(selector1, selector2))
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInf() {
		return new ApiInfoBuilder()
				.title("swagger构建api文档")
				.description("构建RESTful APIs")
				.version("1.0")
				.build();
	}

}
