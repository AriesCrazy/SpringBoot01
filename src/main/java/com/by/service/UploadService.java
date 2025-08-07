package com.by.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.by.dto.FileInfo;
import com.by.dto.UploadInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service //组件
public class UploadService {

    @Value("${upload.deamon}")
    private String deamon;
    @Value("${upload.path}")
    private String path;

    //nginx 1:静态资源服务器

    public String upload(UploadInfo uploadInfo) {
        String name = IdUtil.getSnowflakeNextIdStr() + "_" + uploadInfo.getName();
        String base64 = uploadInfo.getBase64();
        String[] base64Array = StrUtil.splitToArray(base64, ",");
        byte[] bytes = Base64.decode(base64Array[1]);
        FileUtil.writeBytes(bytes, path + name);
        return deamon + "/images/" + name;
    }

    public List<FileInfo> select() {
        /*List<FileInfo> fileList=new ArrayList<>();
        File[] files = FileUtil.ls(path);
        for (File file : files) {
            FileInfo fileInfo = new FileInfo();
            fileInfo.setName(file.getName());
            fileInfo.setUrl(deamon+"/images/"+file.getName());
            fileList.add(fileInfo);
        }
        return fileList;*/

        File[] files = FileUtil.ls(path);
        List<FileInfo> fileInfos = Arrays.stream(files)
                .map(file -> {
                    FileInfo fileInfo = new FileInfo();
                    fileInfo.setName(file.getName());
                    fileInfo.setUrl(deamon + "/images/" + file.getName());
                    return fileInfo;
                }).collect(Collectors.toList());
        return fileInfos;
    }
}
