package co.istad.bankingapp.api.file.web;

import co.istad.bankingapp.api.file.FileService;
import co.istad.bankingapp.api.file.web.FileDto;
import co.istad.bankingapp.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    private  final FileService fileService;

    @GetMapping
    public BaseRest<?> listAllfiles(){
       List<FileDto> fileDtos = fileService.listAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been founded...!")
                .timestamp(LocalDateTime.now())
                .data(fileDtos)
                .build();
    }
    @GetMapping("/{name}")
    public BaseRest<?> findFileByName(@PathVariable String name){
        FileDto fileDto = fileService.findFileByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been founded...!")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart MultipartFile file){
        FileDto fileDto =  fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded...!")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart List<MultipartFile> files){
        List<FileDto> fileDto =  fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    @DeleteMapping("/{name}")
    public BaseRest<?> deleteFileByName(@PathVariable String name){
        fileService.deleteFileByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been deleted..!")
                .timestamp(LocalDateTime.now())
                .data(name)
                .build();
    }
    @DeleteMapping
    public BaseRest<?> deleteAllFiles(){
        String message=  fileService.deleteAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been deleted...!")
                .timestamp(LocalDateTime.now())
                .data(message)
                .build();
    }
    @GetMapping("/download/{name}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable String name) {
        return fileService.download(name);
    }

}
