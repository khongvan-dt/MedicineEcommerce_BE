package aptech.vn.backend.service;

import aptech.vn.backend.dto.MessageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    MessageDTO.GetDto saveOrUpdate(MessageDTO.SaveDto messageDTO);
    Optional<MessageDTO.GetDto> findById(Long id);
    List<MessageDTO.GetDto> findAll();
    Page<MessageDTO.GetDto> findAll(Pageable pageable);
    void deleteById(Long id);
    List<MessageDTO.GetDto> findBySenderId(Long senderId);
    List<MessageDTO.GetDto> findByReceiverId(Long receiverId);
    List<MessageDTO.GetDto> findConversation(Long user1Id, Long user2Id);
    List<MessageDTO.GetDto> findConversationPaged(Long user1Id, Long user2Id, Pageable pageable);
}