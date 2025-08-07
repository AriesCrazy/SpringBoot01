package com.by.controller;

import com.by.dto.FileInfo;
import com.by.dto.UploadInfo;
import com.by.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping//?enumClassName=ProductStatus
    public String upload(@RequestBody UploadInfo UploadInfo) {
        return uploadService.upload(UploadInfo);
    }

    @GetMapping//?enumClassName=ProductStatus
    public List<FileInfo> select() {
        return uploadService.select();
    }
}
