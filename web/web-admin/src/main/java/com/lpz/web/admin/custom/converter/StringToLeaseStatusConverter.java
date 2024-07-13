//package com.lpz.web.admin.custom.converter;
//
//import com.lpz.model.enums.ItemType;
//import com.lpz.model.enums.LeaseStatus;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StringToLeaseStatusConverter implements Converter<String , LeaseStatus> {
//    @Override
//    public LeaseStatus convert(String code) {
//        //LeaseStatus.class.getEnumConstants();同样获取枚举类实例对象
//        LeaseStatus[] values = LeaseStatus.values();
//        for (LeaseStatus leaseStatus:values) {
//            //把String code转换成Integer类型
//
//        }
//        throw new IllegalArgumentException("code:"+code+"非法!");
//    }
//}
