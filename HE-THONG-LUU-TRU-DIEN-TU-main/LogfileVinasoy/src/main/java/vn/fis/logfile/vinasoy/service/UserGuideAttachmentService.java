package vn.fis.logfile.vinasoy.service;

import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;

public interface UserGuideAttachmentService {
    UserGuideAttachment create(MultipartFile multipartFile);
    UserGuideAttachment findone();
    UserGuideAttachment findByid( Long id);


}
