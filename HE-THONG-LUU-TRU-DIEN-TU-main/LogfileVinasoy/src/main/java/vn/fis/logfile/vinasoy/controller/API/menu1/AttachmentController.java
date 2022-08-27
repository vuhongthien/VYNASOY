package vn.fis.logfile.vinasoy.controller.API.menu1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.ResponseMessage.ResponseProcess;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.service.SuggestionService;
import vn.fis.logfile.vinasoy.service.impl.AttachmentManagerSProServiceImpl;

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
import java.util.List;
import java.util.stream.Collectors;

import static vn.fis.logfile.vinasoy.constant.Constant.*;


@RestController
@RequestMapping("/api/attachment/spro")
public class AttachmentController {
    @Autowired
    AttachmentManagerSProServiceImpl attachmentSProService;
    @Autowired
    SuggestionService suggestionService;


    @GetMapping("/suggestion")
    public List<SuggestionSproDto> find7file(@RequestParam("username") String username) {
        return suggestionService.findAll(username);
    }
    @GetMapping("/process")
    public List<Process_SPro_Dto> board(@RequestParam("username") String username) {
        return attachmentSProService.findAllprocess(username);
    }
    @GetMapping("/ticket/{idProcess}")
    public List<Ticket_SPro_Dto> ticket(@RequestParam("username") String username,
                                        @PathVariable(name = "idProcess") Long idProcess) {
        return attachmentSProService.findAllticket(username,idProcess);
    }
    @RequestMapping(value = {"/read/{id}"}, method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, @PathVariable(required = false) Long id) throws ServletException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        Attachment_SPro_Dto attachment = new Attachment_SPro_Dto();
        attachment = attachmentSProService.findByIdAttachment(id);
        String mineType = attachment.getType();

        try {
            String attachmentPath = PATH_LOCAL+attachment.getServerFilePath();

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
                    resp.setHeader("Content-disposition", " inline; filename=" + URLEncoder.encode(attachment.getOriginalFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
                } else {
                    resp.setHeader("Content-disposition", " inline; filename*=UTF-8''" + URLEncoder.encode(attachment.getOriginalFileName(), StandardCharsets.UTF_8.toString()).replace("+", "%20"));
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
    @PostMapping(value = "/savefile-comment")
    public Attachment saveOneAttachmentInTicket(@RequestParam("multipartFile") MultipartFile multipartFile,
                                                @RequestParam("idOwner") Long idOwner,
                                                @RequestParam("username") String username,
                                                @RequestParam("moduleCode") String moduleCode,
                                                @RequestParam("idTicket") Long idTicket,
                                                @RequestParam("ticketName") String ticketName,
                                                @RequestParam("idProcess") Long idProcess,
                                                @RequestParam("processName") String processName
                                                //@RequestParam("processCreatedAt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime processCreatedAt,
                                                //@RequestParam("ticketCreatedAt")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ticketCreatedAt
    ) throws IOException {
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime processCreatedAt = TIME_PROCESS;
        LocalDateTime ticketCreatedAt = TIME_TICKET;
        attachmentSProService.saveComment(idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);
        attachmentSProService.saveOneAttachmentInCommentTicket(multipartFile, idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);
        return attachmentSProService.saveOneAttachmentInTicket(multipartFile, idOwner,username, createdAt, moduleCode, idTicket, ticketName, ticketCreatedAt,
                idProcess, processName, processCreatedAt);

    }

}
