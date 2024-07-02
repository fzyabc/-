package com.fzy.controller;

import com.fzy.service.FileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @PostMapping("/{bucket}/upload")
    String upload(@PathVariable String bucket, MultipartFile file)throws Exception{
        return fileService.upload(bucket,file);
    }

}