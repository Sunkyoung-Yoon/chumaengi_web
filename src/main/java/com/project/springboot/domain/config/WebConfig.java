package com.project.springboot.domain.config;

import com.project.springboot.web.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 로그인 여부에 따른 접속 가능 페이지 구분
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                // 아까 만든 LoginCheckInterceptor 클래스 내용을 넘김
                .order(1)
                // order : 해당 인터셉터가 적용되는 순서, 1이면 첫번째로 실행
                .addPathPatterns("/**")
                // 현재 프로젝트의 모든 주소에 대해 인터셉터를 적용
                .excludePathPatterns("/", "/user/signup","/user/login","/user/finduserid","/user/finduserpw",
                        "/img/**","/user/logout" ,"/css/**","/js/**","/api/**");
        // 그중에 이 주소는 제외
    }

    /**
     * application.properties
     */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:////C:/Users/ysk/IdeaProjects/chumaengi_web/src/main/resources/static/files/");
        //registry.addResourceHandler("/files/**").addResourceLocations(uploadPath);
        //registry.addResourceHandler("/files/**").addResourceLocations(uploadPath);
        //registry.addResourceHandler("/files/**").addResourceLocations(uploadPath);
    }


}

