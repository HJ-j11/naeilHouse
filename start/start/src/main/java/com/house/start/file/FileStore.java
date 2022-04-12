package com.house.start.file;

import com.house.start.domain.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {

    public UploadFile storeFile(MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        // 업로드 한 이미지가 없을 때
        if (multipartFile.isEmpty()) {
            return null;
        }

        // 업로드 한 이미지가 있을 때
        String originalFilename = multipartFile.getOriginalFilename(); // 파일 원본 이름
        String storeFileName = createStoreFileName(originalFilename); // (서버에 저장할 때) 파일 이름

        UploadFile uploadFile = new UploadFile();
        uploadFile.setUploadFileName(originalFilename);
        uploadFile.setStoreFileName(storeFileName);

        // 서버 저장 경로 탐색
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/upload"); // 절대 경로 탐색
//        log.info("realPath={}", realPath);

        // webapp/upload/{storeFileName}.png 경로로 저장됨
        String fullPath = realPath + "/" + storeFileName;
//        log.info("파일 저장 fullPath={}", fullPath);
        multipartFile.transferTo(new File(fullPath));

        return uploadFile;
    }

    // 서버에 저장할 파일 이름 생성 (ex : {uuid}.png)
    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    // 확장자 정보 추출 (ex : .png)
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }

}
