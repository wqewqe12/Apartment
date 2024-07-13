package com.lpz.web.admin.custom.config;

import com.lpz.web.admin.custom.converter.StringEnumConverterFactory;
//import com.lpz.web.admin.custom.converter.StringToItemTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class webMvcConfiguration implements WebMvcConfigurer {
//    @Autowired
//    private StringToItemTypeConverter stringToItemTypeConverter;
    @Autowired
    private StringEnumConverterFactory stringEnumConverterFactory;
    @Override
    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(this.stringToItemTypeConverter);
        registry.addConverterFactory(this.stringEnumConverterFactory);
    }
}
