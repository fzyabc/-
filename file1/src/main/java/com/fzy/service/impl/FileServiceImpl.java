package com.fzy.service.impl;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fzy.entity.PmsFile;
import com.fzy.service.FileService;
import com.fzy.service.IPmsFileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class FileServiceImpl implements FileService {

    @Value("${minio.url}")
    String url;
    @Value("${minio.username}")
    String username;
    @Value("${minio.password}")
    String password;
@Resource
    IPmsFileService pmsFileService;
    @Override
    public String upload(String bucket, MultipartFile file) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {

        //获取上传文件的 md5 大小 类型
        //取得当前文件的md5
        String md5= DigestUtils.md5DigestAsHex(file.getInputStream());
//取得当前文件的大小和类型
        long size = file.getSize();
        String type = file.getContentType();
        PmsFile pmsFile = pmsFileService.get(md5,size,type);
        //如果上传过 无需再传 直接将以前上传过的图片路径返回即可
        if (null != pmsFile) {
            return pmsFile.getPath();
        }
        //从此处开始 新图片上传 -----------------------------------------------------------------------------
        StringBuilder fileName = new StringBuilder(NanoIdUtils.randomNanoId());
        fileName.append(".").append(FileNameUtils.getExtension(file.getOriginalFilename()));

        //创建客户端 与minio服务器进行交互
        MinioClient client = MinioClient.builder()
                .endpoint(url)
                .credentials(username,password)
                .build();

        //构建上传文件所需参数
        PutObjectArgs args = PutObjectArgs.builder()
                .bucket(bucket) //构建 图片应该上传到哪个桶里面
                .contentType(file.getContentType()) //构建 文件的类型  image/jpeg
                .object(fileName.toString()) //构建 文件的名称
                /*
                    .stream 构建文件的内容
                            该方法最后一个参数 partSize表示为较大的文件进行切块传递 0表示不切块
                 */
                .stream(file.getInputStream(),file.getSize(),0)
                .build();
        //上传文件
        client.putObject(args);

        String path = "/" + bucket + "/" + fileName.toString();
        pmsFile = new PmsFile(md5,size,type,path);
        pmsFileService.save(pmsFile);

        //返回文件上传的路径  格式： /桶名/文件名
        return path;
    }
}
