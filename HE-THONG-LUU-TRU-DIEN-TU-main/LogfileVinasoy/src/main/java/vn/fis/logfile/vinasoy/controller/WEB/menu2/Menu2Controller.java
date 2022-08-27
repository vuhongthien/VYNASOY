package vn.fis.logfile.vinasoy.controller.WEB.menu2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.fis.logfile.vinasoy.dto.SFLASH.Attachment_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Board_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.List_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Task_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.SuggestionSproDto;
import vn.fis.logfile.vinasoy.model.UserGuideAttachment;
import vn.fis.logfile.vinasoy.service.I_Menu2Service;
import vn.fis.logfile.vinasoy.service.SuggestionService;
import vn.fis.logfile.vinasoy.service.UserGuideAttachmentService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/web/attachment")
public class Menu2Controller {
    @Autowired
    SuggestionService suggestionService;
    @Autowired
    I_Menu2Service menu2Service;
    @Autowired
    UserGuideAttachmentService userGuideAttachmentService;

    @GetMapping(value ={"/menu2"} )
    public String menu2(Model model, Principal principal) {
        String userName = principal.getName();
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        List<SuggestionSproDto> suggestionSproDtos = suggestionService.findAll(userName);
        List<Board_SFlash_Dto> listBoards = menu2Service.getAllBoardManager();
        model.addAttribute("FL", suggestionSproDtos);
        model.addAttribute("listBoards", listBoards);

        return "index-2";
    }


    //List thư mục list-task
    @GetMapping(value ={"/menu2/{idBoard}"} )
    public String list( Model model, @PathVariable(name = "idBoard") Long idBoard,  Principal principal) {
        List<List_SFlash_Dto> listLists = menu2Service.getAllListByBoardManager(idBoard);
        String userName = principal.getName();
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        Board_SFlash_Dto nameBoard= menu2Service.findBoardById(idBoard);
        model.addAttribute("nameBoard",nameBoard);
        model.addAttribute("listLists", listLists);
        return "index-2-1";
    }

    //List thư mục list-task
    @GetMapping(value ={"/menu2/{idBoard}/{idList}"} )
    public String task( Model model, @PathVariable(name = "idBoard") Long idBoard,
                        @PathVariable(name = "idList") Long idList, Principal principal) {
        List<Task_SFlash_Dto> listTasks = menu2Service.getAllTaskByListAndBoardManager(idBoard,idList);
        String userName = principal.getName();
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        Board_SFlash_Dto nameBoard= menu2Service.findBoardById(idBoard);
        List_SFlash_Dto nameList= menu2Service.findListById(idList);
        model.addAttribute("nameBoard",nameBoard);
        model.addAttribute("nameList",nameList);
        model.addAttribute("listTasks", listTasks);
        return "index-2-1-1";
    }

    //List thư mục task
    @GetMapping(value ={"/menu2/{idBoard}/{idList}/{idTask}"} )
    public String attachment_task( Model model, @PathVariable(name = "idBoard") Long idBoard,
                                   @PathVariable(name = "idList") Long idList,Principal principal
    , @PathVariable(name = "idTask") Long idTask) {
        List<Attachment_SFlash_Dto> listAttachments = menu2Service.getAllAttachmentByTaskListBoardManager(idBoard,idList,idTask);
        String userName = principal.getName();
        UserGuideAttachment userGuideAttachment = userGuideAttachmentService.findone();
        model.addAttribute("guide",userGuideAttachment);
        model.addAttribute("username", userName);
        Board_SFlash_Dto nameBoard= menu2Service.findBoardById(idBoard);
        List_SFlash_Dto nameList= menu2Service.findListById(idList);
        Task_SFlash_Dto nameTask= menu2Service.findTaskById(idTask);
        model.addAttribute("nameBoard",nameBoard);
        model.addAttribute("nameList",nameList);
        model.addAttribute("nameTask",nameTask);
        model.addAttribute("listAttachments", listAttachments);
        return "index-2-1-1-1";
    }
}
