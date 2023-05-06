package co.istad.bankingapp.api.file;

import co.istad.bankingapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


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
}
