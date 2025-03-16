package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MessageDTO;
import aptech.vn.backend.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {
    @Mapping(target = "senderId", source = "sender.id")
    @Mapping(target = "receiverId", source = "receiver.id")
    MessageDTO.GetMessageDto toGetMessageDto(Message entity);

    Message toMessageEntity(MessageDTO.SaveMessageDto dto);
}