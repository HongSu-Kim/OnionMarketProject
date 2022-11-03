package com.youprice.onion.config;

import com.youprice.onion.security.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

// @LoginUser 어노테이션 사용하기 위해 필요한 LoginUserArgumentResolver가 인식될 수 있도록 하는 config
@RequiredArgsConstructor
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		List<String> imageFolders = Arrays.asList("chat", "member", "notice", "product", "review");

		for (String imageFolder : imageFolders) {

			String path = "file:///" + System.getProperty("user.dir").substring(3) + "/src/img/" + imageFolder;

			File folder = new File(System.getProperty("user.dir") + "/src/img/" + imageFolder);
			if (!(folder.exists())) {
				folder.mkdirs();
				log.info("폴더 생성 : " + path);
			}

			registry.addResourceHandler("/img/" + imageFolder + "/**")
					.addResourceLocations(path + "/")
					.setCachePeriod(3600)
					.resourceChain(true)
					.addResolver(new PathResourceResolver());
		}
	}
}
