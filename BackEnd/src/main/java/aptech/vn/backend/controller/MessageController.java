package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MessageDTO;
import aptech.vn.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin("*")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO.GetDto>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO.GetDto> getMessageById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sent/{senderId}")
    public ResponseEntity<List<MessageDTO.GetDto>> getMessagesBySenderId(@PathVariable Long senderId) {
        return ResponseEntity.ok(messageService.findBySenderId(senderId));
    }

    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<MessageDTO.GetDto>> getMessagesByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(messageService.findByReceiverId(receiverId));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDTO.GetDto>> getConversation(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id) {
        return ResponseEntity.ok(messageService.findConversation(user1Id, user2Id));
    }

    @GetMapping("/conversation/paged")
    public ResponseEntity<List<MessageDTO.GetDto>> getConversationPaged(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(messageService.findConversationPaged(user1Id, user2Id, pageable));
    }

    @PostMapping("/save")
    public ResponseEntity<MessageDTO.GetDto> saveOrUpdateMessage(@RequestBody MessageDTO.SaveDto messageDTO) {
        MessageDTO.GetDto savedMessage = messageService.saveOrUpdate(messageDTO);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (!messageService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}