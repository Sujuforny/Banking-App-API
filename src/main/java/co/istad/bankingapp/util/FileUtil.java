package co.istad.bankingapp.util;

import co.istad.bankingapp.api.file.web.FileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.download-image}")
    private String fileDownloadImage;
    public FileDto upload(MultipartFile file){
        System.out.println(file.getSize());
        if (file.getSize() > 1000000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File size exceeds the maximum allowed size of 1MB");
        }
        int lastDotIndex = file.getOriginalFilename().lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex+1);
        long size =file.getSize();
        String name = String.format("%s.%s", UUID.randomUUID(),extension);
        String url = String.format("%s%s",fileBaseUrl,name);
        String urlDownload =String.format("%s%s",fileDownloadImage,name);
        Path path= Paths.get(fileServerPath + name);
        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(name)
                    .url(url)
                    .urlDownload(urlDownload)
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Upload file failed...! ");
        }
    }
}
