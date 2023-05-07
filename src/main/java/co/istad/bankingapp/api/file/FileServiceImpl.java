package co.istad.bankingapp.api.file;

import co.istad.bankingapp.api.file.web.FileDto;
import co.istad.bankingapp.util.FileUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import java.net.MalformedURLException;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;



@Service
public class FileServiceImpl implements FileService{
    private FileUtil fileUtil;
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;

    @Override
    public FileDto uploadSingle(MultipartFile file) {
       return fileUtil.upload(file);
    }

    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto> fileDtos = new ArrayList<>();
        for(MultipartFile file : files){
            fileDtos.add(fileUtil.upload(file));
        }
        return fileDtos;
    }

    @Override
    public List<FileDto> listAllFiles() {
            List<FileDto> fileDtos = new ArrayList<>();
            try {
                Files.walk(Paths.get(fileServerPath))
                        .filter(Files::isRegularFile)
                        .forEach(path -> {
                            File file = path.toFile();
                            String name = file.getName();
                            String extension = name.substring(name.lastIndexOf(".") + 1);
                            long size = file.length();
                            String url = String.format("%s%s", fileBaseUrl, name);
                            fileDtos.add(FileDto.builder()
                                    .name(name)
                                    .url(url)
                                    .extension(extension)
                                    .size(size)
                                    .build());
                        });
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get list of files");
            }
            return fileDtos;
        }

    @Override
    public FileDto findFileByName(String name) {
            Path path = Paths.get(fileServerPath, name);
            File file = path.toFile();

            if (file.exists() && file.isFile()) {
                String extension = name.substring(name.lastIndexOf(".") + 1);
                long size = file.length();
                String url = String.format("%s%s", fileBaseUrl, name);

                return FileDto.builder()
                        .name(name)
                        .url(url)
                        .extension(extension)
                        .size(size)
                        .build();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        }

    @Override
    public void deleteFileByName(String name) {
            Path path = Paths.get(fileServerPath, name);
            File file = path.toFile();

            if (file.exists() && file.isFile()) {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete file");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        }

    @Override
    public String deleteAllFiles() {
        try {
            Files.walk(Paths.get(fileServerPath))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete files");
        }
        return "Delete successfully...!";
    }

    @Override
    public ResponseEntity<UrlResource> download(String name) {
        try {
            Path filePath = Paths.get(fileServerPath, name);
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
                headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM.toString());
                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "File not found");
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to download file");
        }
    }




}
