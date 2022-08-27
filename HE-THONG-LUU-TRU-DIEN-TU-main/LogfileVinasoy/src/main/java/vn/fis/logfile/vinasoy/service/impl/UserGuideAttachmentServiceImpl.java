package vn.fis.logfile.vinasoy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.repository.UserGuideAttachmentRepository;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.saveFile;

@Service
public class UserGuideAttachmentServiceImpl implements UserGuideAttachmentService {
    @Autowired
    UserGuideAttachmentRepository userGuideAttachmentRepository;
    @Override
    public UserGuideAttachment create(MultipartFile multipartFile) {
        String path="src\\main\\resources\\static\\guide";
        UserGuideAttachment userGuideAttachment = new UserGuideAttachment();
        userGuideAttachment.setDateTime(LocalDateTime.now());
        userGuideAttachment.setName(multipartFile.getOriginalFilename());
        userGuideAttachment.setSize(multipartFile.getSize());
        File file = new File(path+File.separator+multipartFile.getOriginalFilename());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userGuideAttachmentRepository.save(userGuideAttachment);
    }

    @Override
    public UserGuideAttachment findone() {
        return userGuideAttachmentRepository.findone();
    }

    @Override
    public UserGuideAttachment findByid(Long id) {
        return userGuideAttachmentRepository.findById(id).get();
    }
}
