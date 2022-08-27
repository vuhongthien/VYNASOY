package vn.fis.logfile.vinasoy.controller.API.menu6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.ResponseMessage.ResponseCheckFile;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.security.Principal;

import static vn.fis.logfile.vinasoy.utils.SHA256Utils.sha256hash;

@RestController
@RequestMapping("/api/attachment")
public class CheckFileController {
    @GetMapping("/check")
    public ResponseCheckFile check(@RequestParam("file1") MultipartFile file1,
                                   @RequestParam("file2")MultipartFile file2) {
        String f1= sha256hash(file1);
        String f2 = sha256hash(file2);
        if(!f2.equals(f1) ){
            System.out.printf(sha256hash(file1)+"\n");
            System.out.printf(sha256hash(file2));
            return  new ResponseCheckFile(0L);
        }
        return  new ResponseCheckFile(1L);
    }
}
