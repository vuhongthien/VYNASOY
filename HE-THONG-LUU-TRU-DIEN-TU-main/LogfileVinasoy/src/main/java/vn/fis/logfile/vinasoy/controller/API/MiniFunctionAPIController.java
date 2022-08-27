package vn.fis.logfile.vinasoy.controller.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static vn.fis.logfile.vinasoy.utils.SHA256Utils.sha256hash;

@RestController
@RequestMapping("/api/mini-function")
public class MiniFunctionAPIController {

    @GetMapping(path = "/createFolder")
    public void createFolder(@RequestParam("path") String path) throws IOException {
        Path dir = Paths.get(path); // filePathServer
        Files.createDirectories(dir);
    }

    @GetMapping(path = "/sha256")
    public String sha256(@RequestParam("multipartFiles") MultipartFile multipartFile){
        return sha256hash(multipartFile);
    }
}
