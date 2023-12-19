package com.ssafy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.interceptor.JWTInterceptor;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	private JWTInterceptor jwtinterceptor;
	private final String uploadPath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/upload/**")
				.addResourceLocations("file:///" + System.getProperty("user.dir") + "/src/main/upload/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}


	@Autowired
	public WebConfiguration(@Value("${file.path.upload-images}") String uploadFilePath, JWTInterceptor jwtinterceptor) {
		super();
		this.jwtinterceptor = jwtinterceptor;
		this.uploadPath = uploadFilePath;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
				.allowedOrigins("*")
//		.allowedOrigins("http://localhost:8080", "http://localhost:8081")
				.allowedMethods(HttpMethod.GET.name(),
						HttpMethod.POST.name(),
						HttpMethod.PUT.name(),
						HttpMethod.DELETE.name(),
						HttpMethod.HEAD.name(),
						HttpMethod.OPTIONS.name(),
						HttpMethod.PATCH.name())
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD")
				.maxAge(3600);
	}


}
