package aptech.vn.backend.service;

import aptech.vn.backend.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    MessageDTO.GetMessageDto saveOrUpdate(MessageDTO.SaveMessageDto messageDTO);
    Optional<MessageDTO.GetMessageDto> findById(Long id);
    List<MessageDTO.GetMessageDto> findAll();
    Page<MessageDTO.GetMessageDto> findAll(Pageable pageable);
    void deleteById(Long id);
    List<MessageDTO.GetMessageDto> findBySenderId(Long senderId);
    List<MessageDTO.GetMessageDto> findByReceiverId(Long receiverId);
    List<MessageDTO.GetMessageDto> findConversation(Long user1Id, Long user2Id);
    List<MessageDTO.GetMessageDto> findConversationPaged(Long user1Id, Long user2Id, Pageable pageable);
}