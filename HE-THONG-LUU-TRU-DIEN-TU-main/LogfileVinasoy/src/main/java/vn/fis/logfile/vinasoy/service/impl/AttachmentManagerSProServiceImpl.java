package vn.fis.logfile.vinasoy.service.impl;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;

import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.mapper.AttachmentMapper;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.repository.AttachmentRepository;
import vn.fis.logfile.vinasoy.service.I_AttachmentManagerSProService;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static vn.fis.logfile.vinasoy.constant.Constant.PATH_LOCAL;
import static vn.fis.logfile.vinasoy.utils.FileHandlerUtils.*;
import static vn.fis.logfile.vinasoy.utils.FileNameUtils.createServerCommentFileNameSPROUtils;
import static vn.fis.logfile.vinasoy.utils.FileNameUtils.createServerFileNameSPROUtils;
import static vn.fis.logfile.vinasoy.utils.FilePDFUtils.addMoreContentpdf;
import static vn.fis.logfile.vinasoy.utils.FileWriterUtils.addMoreContent;

@Service
public class AttachmentManagerSProServiceImpl implements I_AttachmentManagerSProService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private AttachmentMapper attachmentMapper;
    @Transactional
    public List<Attachment> closeTicket(MultipartFile[] multipartFiles, MultipartFile[] commentFiles,
                                           Long idEmployee, String moduleCode, String processCode,
                                           LocalDateTime ticketCreatedAt, String idTicket, String subject) {
        return new ArrayList<>();
    }


    @Override
    public Attachment saveOneAttachmentInTicket(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt, String moduleCode,
                                                   Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                                   String processName, LocalDateTime processCreatedAt) throws IOException {
        createFolder(PATH_LOCAL + "WF1");
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess);
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket);

        Attachment attachmentTicket = new Attachment();
        attachmentTicket.setIdOwner(idOwner);
        attachmentTicket.setUsername(username);
        attachmentTicket.setOriginalFileName(multipartFile.getOriginalFilename());
        int STT_MAX = getSTT_MAX(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket);
        String customFileName = createServerFileNameSPROUtils(moduleCode, idProcess, ticketCreatedAt,
                idTicket, ticketName, String.valueOf(STT_MAX + 1));
        attachmentTicket.setServerFileName(customFileName);
        attachmentTicket.setCreatedAt(createdAt);
        attachmentTicket.setModuleCode(moduleCode);
        attachmentTicket.setIdTicket(idTicket);
        attachmentTicket.setTicketName(ticketName);
        attachmentTicket.setTicketCreatedAt(ticketCreatedAt);
        attachmentTicket.setIdProcess(idProcess);
        attachmentTicket.setProcessName(processName);
        attachmentTicket.setProcessCreatedAt(processCreatedAt);
        attachmentTicket.setStar(false);
        attachmentTicket.setType("TICKET");

        Attachment beginAttachmentTicket = attachmentRepository.save(attachmentTicket);
        Long idAttachmentTicket = beginAttachmentTicket.getId();
        beginAttachmentTicket.setHyperlinkAttachment(createLinkAttachment(idAttachmentTicket));
        String serverPath = "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + idAttachmentTicket+".pdf";
        beginAttachmentTicket.setServerFilePath(serverPath);

        Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachmentTicket);
        String pathfilecomment = "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comment.pdf"; // only WF1/IdTicket/Comment.pdf
        String localPathfilecomment = PATH_LOCAL + pathfilecomment;
        addMoreContentpdf(localPathfilecomment,beginAttachmentTicket.getHyperlinkAttachment(),beginAttachmentTicket.getServerFileName());
        // save attachment in localhost/ server
        String localPath = PATH_LOCAL + serverPath;
        saveFile(multipartFile, localPath);
        //create img
        PDDocument document = PDDocument.load(localPath);
        List<PDPage> list = document.getDocumentCatalog().getAllPages();

            PDPage page = list.get(0);
            BufferedImage image = page.convertToImage().getSubimage(0, 0, 800, 500);
            String imgfolder="src\\main\\resources\\static\\pdf";
            File outputfile = new File(imgfolder + File.separator + idAttachmentTicket+".jpg");
            ImageIO.write(image, "jpg", outputfile);
        // return for viewer
        return attachmentAfterCreate;
    }

    public Attachment saveComment(Long idOwner, String username, LocalDateTime createdAt, String moduleCode,
                                  Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                  String processName, LocalDateTime processCreatedAt) {
        createFolder(PATH_LOCAL + "WF1");
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess);
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket);
        // create a file with fileName: __comments
        // serverPath = CODEWF + File.separator + idTicket;
        Attachment attachmentListComment = new Attachment();
        attachmentListComment.setIdOwner(idOwner);
        attachmentListComment.setUsername(username);
        attachmentListComment.setOriginalFileName("CommentFiles in ticket " + idTicket);
        String serverFileName = createServerFileNameSPROUtils(moduleCode, idProcess, ticketCreatedAt,
                idTicket, ticketName, "Comment");
        attachmentListComment.setServerFileName(serverFileName);
        attachmentListComment.setCreatedAt(LocalDateTime.now());

        attachmentListComment.setModuleCode(moduleCode);
        attachmentListComment.setIdTicket(idTicket);
        attachmentListComment.setTicketName(ticketName);
        attachmentListComment.setTicketCreatedAt(ticketCreatedAt);
        attachmentListComment.setIdProcess(idProcess);
        attachmentListComment.setProcessName(processName);
        attachmentListComment.setProcessCreatedAt(processCreatedAt);
        attachmentListComment.setStar(false);
        attachmentListComment.setType("COMMENT-TICKET");
        //serverPath = WF1 + File.separator + idTicket;
        String serverPath = "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comment.txt"; // only WF1/IdTicket/Comment.txt
        attachmentListComment.setServerFilePath(serverPath);
        String localPath = PATH_LOCAL + serverPath; // in disk is F:/Vinasoy/WF1/IdTicket/Comment.txt
        try{
            File commentList = new File(localPath); // tao file tren o cung
            if(commentList.createNewFile()){
                Attachment attachmentAfterCreate = attachmentRepository.save(attachmentListComment); // luu tren database
                Long id = attachmentAfterCreate.getId();
                attachmentAfterCreate.setHyperlinkAttachment(createLinkAttachment(id));
                return attachmentAfterCreate;
            }
        }catch (IOException e){
            return attachmentListComment;
        }
        return attachmentListComment;
    }

    public Attachment saveOneAttachmentInCommentTicket(MultipartFile multipartFile, Long idOwner, String username, LocalDateTime createdAt, String moduleCode,
                                                          Long idTicket, String ticketName, LocalDateTime ticketCreatedAt, Long idProcess,
                                                          String processName, LocalDateTime processCreatedAt) {
        createFolder(PATH_LOCAL + "WF1");
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess );
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket);
        createFolder(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comments");

        Attachment attachmentComment = new Attachment();
        attachmentComment.setIdOwner(idOwner);
        attachmentComment.setUsername(username);
        attachmentComment.setOriginalFileName(multipartFile.getOriginalFilename());
        int STT_MAX = getSTT_MAX(PATH_LOCAL + "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comments");
        String customFileName = createServerCommentFileNameSPROUtils(moduleCode, idProcess, ticketCreatedAt,
                idTicket, ticketName, String.valueOf(STT_MAX + 1));
        attachmentComment.setServerFileName(customFileName);
        attachmentComment.setCreatedAt(createdAt);

        attachmentComment.setModuleCode(moduleCode);

        //String typeOfFile = getTypeOfFile(attachment.getOriginalFileName()); // lấy ra đuôi file: .txt, .docx, .pdf
        attachmentComment.setIdTicket(idTicket);
        attachmentComment.setTicketName(ticketName);
        attachmentComment.setTicketCreatedAt(ticketCreatedAt);
        attachmentComment.setIdProcess(idProcess);
        attachmentComment.setProcessName(processName);
        attachmentComment.setProcessCreatedAt(processCreatedAt);
        attachmentComment.setStar(false);
        attachmentComment.setType("COMMENT-IN-TICKET");

        Attachment beginAttachment = attachmentRepository.save(attachmentComment);
        Long idAttachmentComment = beginAttachment.getId();
          beginAttachment.setHyperlinkAttachment(createLinkAttachment(idAttachmentComment));
        String serverPath = "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comments" +
                File.separator + idAttachmentComment+ ".pdf";
        beginAttachment.setServerFilePath(serverPath);
        Attachment attachmentAfterCreate = attachmentRepository.save(beginAttachment);

        // save attachment in localhost/ server
        String localPathFileComment = PATH_LOCAL + serverPath;
        saveFile(multipartFile, localPathFileComment);

        String pathFileListComment = "WF1" + File.separator + idProcess + File.separator + idTicket + File.separator + "Comment.pdf";
        String localPathFileListComment = PATH_LOCAL + pathFileListComment;
        addMoreContent(localPathFileListComment,localPathFileComment);
        // return for viewer
        return attachmentAfterCreate;
    }

    @Override
    public List<Process_SPro_Dto> findAllprocess(String u) {
        return attachmentRepository.listprocess(u);
    }

    @Override
    public List<Ticket_SPro_Dto> findAllticket(String u, Long id) {
        return attachmentRepository.listticket(u,id);
    }

    @Override
    public Process_SPro_Dto findByIdProcess(String u,Long id) {
        return attachmentRepository.findByidProcess(id,u);
    }

    @Override
    public Ticket_SPro_Dto findByIdTicket(Long id,Long id2,String u) {
        return attachmentRepository.findByidTicket(id,id2,u);
    }

    @Override
    public List<Attachment_SPro_Dto> findAll(Long idticket) {

        return attachmentRepository.findAll().stream()

                .map(attachmentMapper::toDto)

                .filter(line -> {
                    return line.getIdTicket().equals(idticket) ;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Attachment_SPro_Dto findByIdAttachment(Long id) {
        return attachmentRepository.findById(id).map(attachmentMapper::toDto).get();
    }

}
