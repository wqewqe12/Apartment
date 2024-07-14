package com.lpz.web.admin.service.impl;

import com.lpz.common.until.QiNiuUntils;
import com.lpz.web.admin.service.FileService;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final QiNiuUntils qiniuConfig;

    public FileServiceImpl(QiNiuUntils qiniuConfig) {
        this.qiniuConfig = qiniuConfig;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("文件是空的");
        }
        // 创建上传token
        Auth auth = Auth.create(qiniuConfig.getAccessKey(), qiniuConfig.getSecretKey());
        String upToken = auth.uploadToken(qiniuConfig.getBucket());

        // 设置上传配置，Region要与存储空间所属的存储区域保持一致
        Configuration cfg = new Configuration(Region.region2());

        // 创建上传管理器
        UploadManager uploadManager = new UploadManager(cfg);

        String originalFilename = file.getOriginalFilename();
        // 构造文件目录和文件名
        assert originalFilename != null;
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileKey = qiniuConfig.getMkdir() + UUID.randomUUID() + suffix;

        // 上传文件
        Response response = uploadManager.put(file.getInputStream(), fileKey, upToken, null, null);

        // 返回文件url
        if (!response.isOK()) {
            // 打印返回的信息
            System.out.println(response.bodyString());
            throw new RuntimeException("文件上传失败");
        }
        return qiniuConfig.getDomain() +"/"+ fileKey;
    }

//    @Autowired
//    private QiNiuUntils qiNiuUntils;
//    @Override
//    public String upload(MultipartFile file) {
//        String filename = file.getOriginalFilename();
//        //判断文件格式是否为图片
//        if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jepg")){
//            String fileUrl = qiNiuUntils.uploadImageQiniu(file);
//            return fileUrl;
//        }
////        String url = fileService.upload(file);
////        return Result.ok(url);
//        return "文件格式错误";
//    }
}
