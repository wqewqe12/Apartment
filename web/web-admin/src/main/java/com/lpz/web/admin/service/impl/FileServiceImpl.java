package com.lpz.web.admin.service.impl;

import com.lpz.common.until.QiNiuUntils;
import com.lpz.web.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private QiNiuUntils qiNiuUntils;
    @Override
    public String upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        //判断文件格式是否为图片
        if (filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".jepg")){
            String fileUrl = qiNiuUntils.uploadImageQiniu(file);
            return fileUrl;
        }
//        String url = fileService.upload(file);
//        return Result.ok(url);
        return "文件格式错误";
    }
}
