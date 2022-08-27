package vn.fis.logfile.vinasoy.controller.API.menu2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.fis.logfile.vinasoy.dto.SFLASH.Attachment_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Board_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.List_SFlash_Dto;
import vn.fis.logfile.vinasoy.dto.SFLASH.Task_SFlash_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;
import vn.fis.logfile.vinasoy.repository.Menu2Repository;
import vn.fis.logfile.vinasoy.service.I_Menu2Service;

import java.util.List;

@RestController
@RequestMapping("/api/menu2")
public class APIMenu2Controller {

    @Autowired
    I_Menu2Service menu2Service;

    @Autowired
    Menu2Repository menu2Repository;

    @GetMapping(path = "/getSuggestion")
    public void getSuggestion (){
        menu2Service.getSuggestion(2L);
    }

    @GetMapping(path = "/getAllBoardManager")
    public List<Board_SFlash_Dto> getAllBoardManager(){
        return menu2Service.getAllBoardManager();
    }

    @GetMapping(path = "/getAllListByBoardManager")
    public List<List_SFlash_Dto> getAllListByBoardManager(@RequestParam("idBoard") Long idBoard){
        return menu2Service.getAllListByBoardManager(idBoard);
    }

    @GetMapping(path = "/getAllTaskByListAndBoardManager")
    public List<Task_SFlash_Dto> getAllTaskByListAndBoardManager(@RequestParam("idBoard") Long idBoard,
                                                                 @RequestParam("idList") Long idList){
        return menu2Service.getAllTaskByListAndBoardManager(idBoard,idList);
    }

    @GetMapping(path = "/getAllAttachmentByTaskListBoardManager")
    public List<Attachment_SFlash_Dto> getAllAttachmentByTaskListBoardManager(@RequestParam("idBoard") Long idBoard,
                                                                              @RequestParam("idList") Long idList,
                                                                              @RequestParam("idTask") Long idTask){
        return menu2Service.getAllAttachmentByTaskListBoardManager(idBoard,idList,idTask);
    }

    // TEST SIZE
    @GetMapping(path = "/getAllAttachmentTest")
    public List<Attachment> getAllAttachmentTest(){
        return menu2Repository.getAllAttachmentMenu2();
    }


}
