package com.ruochen.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.ruochen.utils.COSConfig;
import com.ruochen.utils.DataInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 图片上传到腾讯云 cos
 */
@Controller
public class UploadController {

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public DataInfo Upload(@RequestParam(value = "file") MultipartFile file, HttpSession session) {
        if (file == null) {
            return DataInfo.fail(-1, "文件为空");
        }
        //获取文件上传原名
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        //通过UUID随机生成新的文件名
        String newFileName = UUID.randomUUID() + eName;

        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(COSConfig.secretId, COSConfig.secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(COSConfig.RegionName));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = COSConfig.bucketName;

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            //创建临时文件
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            String key = COSConfig.cosKeyName + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            String imgUrl = COSConfig.preUrl + key;
            return DataInfo.ok("上传成功", imgUrl);
        } catch (IOException e) {
            return DataInfo.fail(-1, e.getMessage());
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }
}
