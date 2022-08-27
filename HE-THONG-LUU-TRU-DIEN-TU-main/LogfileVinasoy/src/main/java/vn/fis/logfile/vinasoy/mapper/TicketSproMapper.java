package vn.fis.logfile.vinasoy.mapper;

import org.mapstruct.Mapper;
import vn.fis.logfile.vinasoy.dto.SPRO.Process_SPro_Dto;
import vn.fis.logfile.vinasoy.dto.SPRO.Ticket_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;

@Mapper(componentModel = "spring", uses = {})
public interface TicketSproMapper extends EntityMapper<Ticket_SPro_Dto, Attachment>{
}
