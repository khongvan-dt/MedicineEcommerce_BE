package aptech.vn.backend.controller;

import aptech.vn.backend.entity.Message;
import aptech.vn.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sent/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySenderId(@PathVariable Long senderId) {
        return ResponseEntity.ok(messageService.findBySenderId(senderId));
    }

    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(messageService.findByReceiverId(receiverId));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id) {
        return ResponseEntity.ok(messageService.findConversation(user1Id, user2Id));
    }

    @GetMapping("/conversation/paged")
    public ResponseEntity<List<Message>> getConversationPaged(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(messageService.findConversationPaged(user1Id, user2Id, pageable));
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(messageService.save(message), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        return messageService.findById(id)
                .map(existingMessage -> {
                    message.setId(id);
                    return ResponseEntity.ok(messageService.save(message));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        return messageService.findById(id)
                .map(message -> {
                    messageService.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}