package vn.fis.logfile.vinasoy.mapper;

import org.mapstruct.Mapper;
import vn.fis.logfile.vinasoy.dto.SPRO.Attachment_SPro_Dto;
import vn.fis.logfile.vinasoy.model.Attachment;

@Mapper(componentModel = "spring", uses = {})
public interface AttachmentMapper extends EntityMapper<Attachment_SPro_Dto, Attachment>{
}
