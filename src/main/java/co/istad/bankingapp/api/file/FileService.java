package co.istad.bankingapp.api.file;

import co.istad.bankingapp.api.file.web.FileDto;
import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto> uploadMultiple(List<MultipartFile> files);
    List<FileDto> listAllFiles();
    FileDto findFileByName(String name);
    void deleteFileByName(String name);
    String deleteAllFiles();
    public ResponseEntity<UrlResource> download(String filename);
}
