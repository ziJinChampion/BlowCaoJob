package com.sw.file.controller;

import com.sw.common.exception.file.FIleException;
import com.sw.common.pojo.ErrorMessage;
import com.sw.common.pojo.Result;
import com.sw.common.pojo.StatusCode;
import com.sw.file.util.FastDFSClient;
import com.sw.file.util.FastDFSFile;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {


    @PostMapping
    public Result<String> uploadFile(MultipartFile file) {
        try {
            if (file == null) {
                throw new FIleException(ErrorMessage.FILE_NOT_EXIST);
            }
            String originalFilename = file.getOriginalFilename();
            if (!StringUtils.hasLength(originalFilename)) {
                throw new FIleException(ErrorMessage.FILE_NOT_EXIST);
            }
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            byte[] content = file.getBytes();

            FastDFSFile fastDFSFile = new FastDFSFile(originalFilename, content, extName);

            String[] uploadResult = FastDFSClient.upload(fastDFSFile);

            String url = FastDFSClient.getTrackerUrl() + uploadResult[0] + "/" + uploadResult[1];
            return new Result<>(true, StatusCode.OK, url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<>(false, StatusCode.ERROR, ErrorMessage.FILE_UPLOAD_FAIL, ErrorMessage.FILE_UPLOAD_FAIL);
    }
}
