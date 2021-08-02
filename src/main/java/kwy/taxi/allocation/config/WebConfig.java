package kwy.taxi.allocation.config;

import kwy.taxi.allocation.security.MyAuthInterceptor;
import kwy.taxi.allocation.security.MyAuthTokenManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MyAuthTokenManager myAuthTokenManager;

    public WebConfig(MyAuthTokenManager myAuthTokenManager) {
        this.myAuthTokenManager = myAuthTokenManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyAuthInterceptor(myAuthTokenManager))
                .addPathPatterns("/taxi-requests/**")
                .excludePathPatterns("/users/*");
    }
}
