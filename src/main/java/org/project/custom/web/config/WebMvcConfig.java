package org.project.custom.web.config;

import org.project.custom.common.converter.StringToBooleanYnConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 확장 설정
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 요청 파라미터 String -> Boolean 변환기 등록
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToBooleanYnConverter());
    }
}
