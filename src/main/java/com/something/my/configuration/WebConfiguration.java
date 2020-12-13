package com.something.my.configuration;

import com.something.my.configuration.interceptor.AuthInterceptor;
import com.something.my.configuration.interceptor.IndexInterceptor;
import com.something.my.configuration.interceptor.TrialRouteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private static final List<String> TRIAL_ROUTES = List.of("/task", "/mindmap", "/chart",
            "/videocall", "/videocall/*", "/meeting", "/poll", "/banner", "/board", "/html", "/sheet", "/py");


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new IndexInterceptor()).addPathPatterns("/");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/u/**").addPathPatterns("/apis/**");
        registry.addInterceptor(new TrialRouteInterceptor()).addPathPatterns(TRIAL_ROUTES);
    }

}
