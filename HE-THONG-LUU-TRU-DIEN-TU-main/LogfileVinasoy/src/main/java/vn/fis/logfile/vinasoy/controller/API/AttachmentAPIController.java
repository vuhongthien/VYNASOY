package vn.fis.logfile.vinasoy.controller.API;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
@RequestMapping("/api/attachment")

public class AttachmentAPIController {


//    @GetMapping("/{pageNumber}/{pageSize}")
//    public Page<AttachmentDTO> findAll(@PathVariable(name = "pageNumber") Integer pageNumber
//            , @PathVariable(name = "pageSize") Integer pageSize) {
//        log.info("Response All Customer. PageNumber: {}, PageSize: {}", pageNumber, pageSize);
//        return attachmentService.findAll(PageRequest.of(pageNumber, pageSize));
//    }

//    @GetMapping("/findAll")
//    public List<Attachment> findAll() {
//        return attachmentService.findAll();
//    }
//
//    @GetMapping("/findById/{id}")
//    public Attachment findById(@RequestParam("id") Long id) {
//        return attachmentService.findById(id);
//    }

//    @GetMapping("/process/{id}")
//    public List<TICKET> findAllById(@RequestParam("id") LIST<Long>  ID_ATTACHMENT_TICKET) {
//        return attachmentService.findById(id);
//    }

//    @GetMapping(path = "/checksums")
//    public String checksums(@RequestParam("fileFirst") MultipartFile fileFirst, @RequestParam("fileSecond") MultipartFile fileSecond){
//        return attachmentService.checksums(fileFirst,fileSecond);
//    }


    @GetMapping(path = "/getSizeA")
    public Long getSizeA(@RequestParam("path") String path){
       File f = new File(path);
       return f.length();
    }

    @GetMapping(path = "/getSizeB")
    public Long getSizeB(@RequestParam("pathFile") Path pathFile) throws IOException {
        return  Files.size(pathFile);
    }




}
