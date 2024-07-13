//package com.lpz.web.admin.custom.converter;
//
//import com.lpz.model.enums.ItemType;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToItemTypeConverter implements Converter<String , ItemType> {
//    @Override
//    public ItemType convert(String code) {
//        //ItemType.class.getEnumConstants();同样获取枚举类实例对象
//        ItemType[] values = ItemType.values();
//        for (ItemType itemType:values) {
//            //把String code转换成Integer类型
//            if (itemType.getCode().equals(Integer.valueOf(code))){
//                return itemType;
//            }
//        }
//        throw new IllegalArgumentException("code:"+code+"非法!");
//    }
//}
