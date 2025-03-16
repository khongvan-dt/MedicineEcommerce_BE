package aptech.vn.backend.controller;

import aptech.vn.backend.dto.MessageDTO;
import aptech.vn.backend.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<MessageDTO.GetMessageDto>> getAllMessages() {
        return ResponseEntity.ok(messageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDTO.GetMessageDto> getMessageById(@PathVariable Long id) {
        return messageService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/sent/{senderId}")
    public ResponseEntity<List<MessageDTO.GetMessageDto>> getMessagesBySenderId(@PathVariable Long senderId) {
        return ResponseEntity.ok(messageService.findBySenderId(senderId));
    }

    @GetMapping("/received/{receiverId}")
    public ResponseEntity<List<MessageDTO.GetMessageDto>> getMessagesByReceiverId(@PathVariable Long receiverId) {
        return ResponseEntity.ok(messageService.findByReceiverId(receiverId));
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<MessageDTO.GetMessageDto>> getConversation(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id) {
        return ResponseEntity.ok(messageService.findConversation(user1Id, user2Id));
    }

    @GetMapping("/conversation/paged")
    public ResponseEntity<List<MessageDTO.GetMessageDto>> getConversationPaged(
            @RequestParam Long user1Id,
            @RequestParam Long user2Id,
            @PageableDefault(size = 20, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(messageService.findConversationPaged(user1Id, user2Id, pageable));
    }

    @PostMapping("/save")
    @Operation(
            summary = "Save message",
            description = "save message",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = MessageDTO.GetMessageDto.class)
                    )
            )
    )
    public ResponseEntity<MessageDTO.GetMessageDto> saveOrUpdateMessage(@RequestBody MessageDTO.SaveMessageDto messageDTO) {
        MessageDTO.GetMessageDto savedMessage = messageService.saveOrUpdate(messageDTO);
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