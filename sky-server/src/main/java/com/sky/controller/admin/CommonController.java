package com.sky.controller.admin;


import com.sky.properties.AliyunOSSOperator;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin/common")


@Api(tags = "文件上传")
public class CommonController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    @ApiOperation("上传")
    public Result<String> upload(MultipartFile file) throws Exception {
        log.info("接收参数：{}",file.getOriginalFilename());
        String upload = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        return Result.success(upload);

    }

}
