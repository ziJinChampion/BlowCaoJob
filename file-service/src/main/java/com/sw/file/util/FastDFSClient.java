package com.sw.file.util;

import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FastDFSClient {

    @Value("${fdfs.client.conf}")
    private static String FDFSClientConfig;

    static {
        try {
            String filePath = new ClassPathResource(FDFSClientConfig).getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            log.error("FastDFS Client Init Fail!", e);
        }
    }

    public static String[] upload(FastDFSFile file) {
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("author", file.getAuthor());

        String[] uploadResults = null;
        StorageClient storageClient = null;
        try {
            storageClient = getTrackerClient();
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), metaList);
        } catch (Exception e) {
            log.error("Exception when upload file:" + file.getName(), e);
        }

        if (uploadResults == null && storageClient != null) {
            log.error("upload fail, error code is:" + storageClient.getErrorCode());
        }

        return uploadResults;
    }

    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("Exception when get file info");
        }
        return null;
    }

    public static InputStream downLoadFile(String groupName, String remoteFileName) {
        try {
            StorageClient storageClient = getTrackerClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            log.error("Exception: downLoadFile from fdfs failed.", e);
        }
        return null;
    }

    public static void deleteFile(String groupName, String remoteFileName) throws MyException, IOException {
        StorageClient storageClient = getTrackerClient();
        storageClient.delete_file(groupName, remoteFileName);
    }

    public static StorageServer[] getStorages(String groupName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getStoreStorages(trackerServer, groupName);
    }

    public static ServerInfo[] getFetchStorages(String groupName, String remoteFileName) throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
    }

    public static String getTrackerUrl() throws IOException {
        return "http://" + getTrackerServer().getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

    private static StorageClient getTrackerClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        return new StorageClient(trackerServer, null);
    }

    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getConnection();
    }

}
