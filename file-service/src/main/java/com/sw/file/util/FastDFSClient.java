package com.sw.file.util;

import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class FastDFSClient {

    @Value("fdfs.client.conf")
    private static String FDFSClientConfig;

    static {
        try{
            String filePath = new ClassPathResource(FDFSClientConfig).getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        }catch (Exception e){
            log.error("FastDFS Client Init Fail!",e);
        }
    }
}
