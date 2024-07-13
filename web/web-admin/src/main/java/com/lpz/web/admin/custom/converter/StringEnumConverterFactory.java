package com.lpz.web.admin.custom.converter;

import com.lpz.model.enums.BaseEnum;
import com.lpz.model.enums.ItemType;
import org.checkerframework.checker.units.qual.C;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

@Component
public class StringEnumConverterFactory implements ConverterFactory<String , BaseEnum> {

    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {

        return new Converter<String, T>() {
            @Override
            public T convert(String code) {
                T[] enumConstants = targetType.getEnumConstants();
                for (T enumConstant : enumConstants) {
                    if (enumConstant.getCode().equals(Integer.valueOf(code))){
                        return enumConstant;
                    }
                }
                throw new IllegalArgumentException("code:"+code+"非法!");
            }
        };
    }
}
