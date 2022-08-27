package vn.fis.logfile.vinasoy.controller.WEB.guide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Suggestion;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDateTime;

import static vn.fis.logfile.vinasoy.constant.Constant.PATH_LOCAL;

@Controller
@RequestMapping("/web/attachment")
public class UserGuideAttachmentWEBController {
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;
        @GetMapping("/Guide")
    public String findAll(Model model, Principal principal) {
            String userName = principal.getName();
            UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
            model.addAttribute("guide",userGuideAttachment);
            model.addAttribute("username", userName);
        return "index-guide";
    }
    @PostMapping("/GuideCreate")
    public String create(Model model,@RequestParam("multipartFile") MultipartFile multipartFile) {
             userGuideAttachmentService.create(multipartFile);
        return "redirect:/web/attachment/Guide";

    }
    //Read attachment
    @RequestMapping(value = {"/readguide/{id}"}, method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, Principal principal, HttpServletResponse resp, @PathVariable(required = false) Long id) throws ServletException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        UserGuideAttachment userGuideAttachment = new UserGuideAttachment();
        userGuideAttachment = userGuideAttachmentService.findByid(id);

        String mineType = ".pdf";
        try {
            String attachmentPath = "src\\main\\resources\\static\\guide"+File.separator + userGuideAttachment.getName();
            inputStream = Files.newInputStream(Paths.get(attachmentPath));
            // IOUtils jar used to convert Input Stream to byte array easily
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            // read bytes from the input stream and store them in the buffer
            while ((len = inputStream.read(buffer)) != -1) {
                // write bytes from the buffer into the output stream
                os.write(buffer, 0, len);
            }
            byte[] bytes = os.toByteArray();
            MimetypesFileTypeMap mimetypesFileTypeMap = new MimetypesFileTypeMap();
            String _MimetypesFileTypeMap = "";
            switch (mineType) {
                case ".pdf":
                    _MimetypesFileTypeMap = "application/pdf";
                    break;
                case ".jpg":
                    _MimetypesFileTypeMap = "image/jpg";
                    break;
                case ".txt":
                    _MimetypesFileTypeMap = "text/plain";
                    break;
                default:
                    _MimetypesFileTypeMap = "application/octet-stream";
                    break;
            }
            if (new File(attachmentPath).exists()) {
                resp.setContentType(_MimetypesFileTypeMap + "; charset=UTF-8");
                resp.setCharacterEncoding("UTF-8");
                resp.setHeader("X-Frame-Options", "SAMEORIGIN");
                if (req.getHeader("User-Agent").indexOf("IE") != -1 || req.getHeader("User-Agent").indexOf("Chrome") != -1) {
                    resp.setHeader("Content-disposition", " inline; filename=" + URLEncoder.encode(userGuideAttachment.getName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
                } else {
                    resp.setHeader("Content-disposition", " inline; filename*=UTF-8''" + URLEncoder.encode(userGuideAttachment.getName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
                }
                outputStream = resp.getOutputStream();
                outputStream.write(bytes);
//                resp.getOutputStream().write(Files.readAllBytes(Paths.get(attachment.getFileName())));
            } else
                resp.sendError(404, "File not found");
        } catch (Exception e) {
            resp.sendError(404, e.toString());
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (outputStream != null)
                outputStream.close();
        }
    }

}
