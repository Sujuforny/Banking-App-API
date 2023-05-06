package co.istad.bankingapp.api.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
public interface FileService {
    FileDto uploadSingle(MultipartFile file);
    List<FileDto> uploadMultiple(List<MultipartFile> files);
}
