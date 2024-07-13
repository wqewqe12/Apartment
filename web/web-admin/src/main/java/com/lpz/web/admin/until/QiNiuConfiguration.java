package com.lpz.common.upload;


import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
//@ConfigurationProperties(prefix = "qiniuyun.upload")
//@Data
public class QiNiuConfiguration {

        @Value("${qiniuyun.upload.AccessKey}")
        private String AccessKey;

        @Value("${qiniuyun.upload.SecretKey}")
        private String SecretKey;

        @Value("${qiniuyun.upload.BucketName}")
        private String BucketName;

        @Value("${qiniuyun.upload.url}")
        private String url;

        /**
         * 上传图片到七牛云
         * @param multipartFile
         * @return
         */
        public String uploadImageQiniu(MultipartFile multipartFile){
                try {
                        //1、获取文件上传的流
                        byte[] fileBytes = multipartFile.getBytes();
                        //2、创建日期目录分隔
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        String datePath = dateFormat.format(new Date());

                        //3、获取文件名
                        String originalFilename = multipartFile.getOriginalFilename();
                        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
                        String filename = datePath+"/"+UUID.randomUUID().toString().replace("-", "")+ suffix;

                        //4.构造一个带指定 Region 对象的配置类
                        //Region根据自己的对象空间的地址选
                        Configuration cfg = new Configuration(Region.region2());
                        UploadManager uploadManager = new UploadManager(cfg);

                        //5.获取七牛云提供的 token
                        Auth auth = Auth.create(AccessKey, SecretKey);
                        String upToken = auth.uploadToken(BucketName);
                        uploadManager.put(fileBytes, filename, upToken);

                        return url+"/"+filename;
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return null;
        }

}