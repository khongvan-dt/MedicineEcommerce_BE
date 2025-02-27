package aptech.vn.backend.service;

import aptech.vn.backend.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MessageService {
    Message save(Message message);
    Optional<Message> findById(Long id);
    List<Message> findAll();
    Page<Message> findAll(Pageable pageable);
    void deleteById(Long id);
    List<Message> findBySenderId(Long senderId);
    List<Message> findByReceiverId(Long receiverId);
    List<Message> findConversation(Long user1Id, Long user2Id);
    List<Message> findConversationPaged(Long user1Id, Long user2Id, Pageable pageable);
}