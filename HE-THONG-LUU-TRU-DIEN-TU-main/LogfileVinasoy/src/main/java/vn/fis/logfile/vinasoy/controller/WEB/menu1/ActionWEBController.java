package vn.fis.logfile.vinasoy.controller.WEB.menu1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.fis.logfile.vinasoy.ResponseMessage.ResponseCheckFile;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Share;
import vn.fis.logfile.vinasoy.model.Star;
import vn.fis.logfile.vinasoy.model.Suggestion;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSProService;
import vn.fis.logfile.vinasoy.service.ShareService;
import vn.fis.logfile.vinasoy.service.StarService;
import vn.fis.logfile.vinasoy.service.SuggestionService;
import vn.fis.logfile.vinasoy.utils.SHA256Utils.*;

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
import java.util.ArrayList;
import java.util.List;

import static vn.fis.logfile.vinasoy.constant.Constant.PATH_LOCAL;
import static vn.fis.logfile.vinasoy.utils.SHA256Utils.sha256hash;

@Controller
@RequestMapping("/web/attachment")
public class ActionWEBController {
    @Autowired
    ShareService shareService;
    @Autowired
    SuggestionService suggestionService;
    @Autowired
    StarService starService;
    @Autowired
    I_AttachmentManagerSProService i_attachmentManagerSProService;
    // function share process
    @RequestMapping(value ={"/shareprocess"}, method = RequestMethod.GET )
    public String menu1(Model model, Principal principal, @RequestParam ("u") String u, @RequestParam("id") List<Long> listid) {
        try{
            String userName = principal.getName();
            List<Process_SPro_Dto> process_sPro_dtoList = new ArrayList<>();
            for (int i = 0 ; i< listid.size(); i++){
               Process_SPro_Dto a = i_attachmentManagerSProService.findByIdProcess(userName,listid.get(i));
                process_sPro_dtoList.add(a);
                Share share = new Share();
                share.setCreatedAt(a.getProcessCreatedAt());
                share.setCreatedAtShare(LocalDateTime.now());
                share.setNameFileShare(a.getProcessName());
                share.setIdProcess(a.getIdProcess());
                share.setTypeFile(Long.valueOf(3));
                share.setFromUser(userName);
                share.setToUser(u);
                shareService.create(share);
            }
        }catch (Exception e){
            model.addAttribute("status", "Khum có share được nha !");
        }
        return "redirect:/web/attachment/";
    }
    // function share ticket
    @RequestMapping(value ={"/shareticket"}, method = RequestMethod.GET )
    public String menu1ticket(Model model,Principal principal,@RequestParam ("u") String u,
                              @RequestParam("idpro") Long idpro,
                              @RequestParam("id") List<Long> listid) {
        String userName = principal.getName();
        List<Ticket_SPro_Dto> ticket_sPro_dtos = new ArrayList<>();
        try{
            for (int i = 0 ; i< listid.size(); i++){
                Ticket_SPro_Dto a = i_attachmentManagerSProService.findByIdTicket(listid.get(i),idpro,userName);
                ticket_sPro_dtos.add(a);
                Share share = new Share();
                share.setCreatedAt(a.getTicketCreatedAt());
                share.setCreatedAtShare(LocalDateTime.now());
                share.setNameFileShare(a.getTicketName());
                share.setIdTicket(a.getIdTicket());
                share.setTypeFile(Long.valueOf(2));
                share.setFromUser(userName);
                share.setToUser(u);
                shareService.create(share);
            }
        }catch (Exception e){
            model.addAttribute("status", "Khum có share được nha !");
        }
        return "redirect:/web/attachment/menu1/"+idpro;

    }
    // function share attachment
    @RequestMapping(value ={"/shareattachment"}, method = RequestMethod.GET )
    public String menu1attachment(Model model,@RequestParam ("u") String u,
                              Principal principal,
                              @RequestParam("idpro") Long idpro,
                              @RequestParam("idticket") Long idticket,
                              @RequestParam("id") List<Long> listid) {
        try{
            List<Attachment_SPro_Dto> attachment_sPro_dtos = new ArrayList<>();
            String userName = principal.getName();

            for (int i = 0 ; i< listid.size(); i++){
                Attachment_SPro_Dto a = i_attachmentManagerSProService.findByIdAttachment(listid.get(i));
                attachment_sPro_dtos.add(a);
                Share share = new Share();
                share.setCreatedAt(a.getCreatedAt());
                share.setCreatedAtShare(LocalDateTime.now());
                share.setNameFileShare(a.getOriginalFileName());
                share.setTypeFile(Long.valueOf(1));
                share.setIdAttachment(a.getId());
                share.setFromUser(userName);
                share.setToUser(u);
                shareService.create(share);
            }
        }catch (Exception e){
            model.addAttribute("status", "Khum có share được nha !");

        }
        return "redirect:/web/attachment/menu1/"+idpro+"/"+idticket;

    }
    //Read attachment
    @RequestMapping(value = {"/read/{id}"}, method = RequestMethod.GET)
    protected void doGet(HttpServletRequest req,Principal principal, HttpServletResponse resp, @PathVariable(required = false) Long id) throws ServletException, IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        String userName = principal.getName();
        Attachment_SPro_Dto attachment = new Attachment_SPro_Dto();
        attachment = i_attachmentManagerSProService.findByIdAttachment(id);
        Suggestion suggestiondto = suggestionService.findById(id,userName);
        if(suggestiondto == null){
            Suggestion s = new Suggestion();
            s.setIdAttachment(id);
            s.setLastOpened(LocalDateTime.now());
            s.setUsername(userName);
            suggestionService.create(s);
        }else {
            suggestiondto.setLastOpened(LocalDateTime.now());
            suggestionService.create(suggestiondto);
        }
        String mineType = ".pdf";
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
    @GetMapping("/starticket")
    public String addstarticket(@RequestParam("id") List<Long> listid,
                              Model model,
                              Principal principal,
                              @RequestParam("idpro") Long idpro) {
        String userName = principal.getName();
        List<Ticket_SPro_Dto> ticket_sPro_dtos = new ArrayList<>();
        try{
            for (int i = 0 ; i< listid.size(); i++){
            Ticket_SPro_Dto ticket_sPro_dto = i_attachmentManagerSProService.findByIdTicket(listid.get(i),idpro,userName);
            ticket_sPro_dtos.add(ticket_sPro_dto);
            Star star = new Star();
            star.setCreatedAt(ticket_sPro_dto.getTicketCreatedAt());
            star.setFromUser(ticket_sPro_dto.getUsername());
            star.setIdTicket(ticket_sPro_dto.getIdTicket());
            star.setNameFile(ticket_sPro_dto.getTicketName());
            star.setTypeFile(Long.valueOf(2));
            starService.addstar(star);
        }
    }catch (Exception e){
        model.addAttribute("status", "Khum có gắn sao được nha được nha !");
    }
        return "redirect:/web/attachment/menu1/"+idpro;

    }
    @GetMapping("/starprocess")
    public String addstarprocess(Model model, Principal principal, @RequestParam("id") List<Long> listid) {

        try{
            String userName = principal.getName();
            List<Process_SPro_Dto> process_sPro_dtos = new ArrayList<>();
            for (int i = 0 ; i< listid.size(); i++){
                Process_SPro_Dto a = i_attachmentManagerSProService.findByIdProcess(userName,listid.get(i));
                process_sPro_dtos.add(a);
        Star star = new Star();
        star.setCreatedAt(a.getProcessCreatedAt());
        star.setFromUser(a.getUsername());
        star.setIdProcess(a.getIdProcess());
        star.setNameFile(a.getProcessName());
        star.setTypeFile(Long.valueOf(3));
        starService.addstar(star);
    }
}catch (Exception e){
        model.addAttribute("status", "Khum có gắn sao được nha được nha !");
        }
        return "redirect:/web/attachment/";
    }
    @GetMapping("/starattachment")
    public String addstarattachment(Model model,
                                    @RequestParam("idpro") Long idpro,
                                    @RequestParam("idticket") Long idticket,
                                    @RequestParam("id") List<Long> listid) {
        try{
            List<Attachment_SPro_Dto> attachment_sPro_dtos = new ArrayList<>();
            for (int i = 0 ; i< listid.size(); i++){
                Attachment_SPro_Dto a = i_attachmentManagerSProService.findByIdAttachment(listid.get(i));
                attachment_sPro_dtos.add(a);
        Star star = new Star();
        star.setCreatedAt(a.getCreatedAt());
        star.setFromUser(a.getUsername());
        star.setIdAttachment(a.getId());
        star.setTypeFile(Long.valueOf(1));
        star.setNameFile(a.getOriginalFileName());
        starService.addstar(star);
            }
        }catch (Exception e){
            model.addAttribute("status", "Khum có gắn sao được nha được nha !");
        }
        return "redirect:/web/attachment/menu1/"+idpro+"/"+idticket;
    }
    @GetMapping("/check")
    public String check(Model model,
                        @RequestParam("file1")MultipartFile file1,
                        @RequestParam("file2")MultipartFile file2) {
        if(sha256hash(file1) == sha256hash(file2)){
            model.addAttribute("checktrue", new ResponseCheckFile(1L));
        }else {
            model.addAttribute("checkfasle", new ResponseCheckFile(0L));
        }
        return "redirect:/web/attachment/";
    }

}
