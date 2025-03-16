package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.MessageDTO;
import aptech.vn.backend.entity.Message;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.MessageMapper;
import aptech.vn.backend.repository.MessageRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(
            MessageRepository messageRepository,
            UserRepository userRepository,
            MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDTO.GetMessageDto saveOrUpdate(MessageDTO.SaveMessageDto messageDTO) {
        Message message;

        if (messageDTO.getId() == null || messageDTO.getId() == 0) {
            // INSERT case
            message = new Message();
            message.setCreatedAt(LocalDateTime.now());
            message.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Message> existingMessage = messageRepository.findById(messageDTO.getId());
            if (existingMessage.isEmpty()) {
                throw new RuntimeException("Message not found with ID: " + messageDTO.getId());
            }
            message = existingMessage.get();
            message.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý sender relationship
        User sender = userRepository.findById(messageDTO.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found with ID: " + messageDTO.getSenderId()));
        message.setSender(sender);

        // Xử lý receiver relationship
        User receiver = userRepository.findById(messageDTO.getReceiverId())
                .orElseThrow(() -> new RuntimeException("Receiver not found with ID: " + messageDTO.getReceiverId()));
        message.setReceiver(receiver);

        // Cập nhật nội dung
        message.setContent(messageDTO.getContent());

        Message savedMessage = messageRepository.save(message);
        return messageMapper.toGetMessageDto(savedMessage);
    }

    @Override
    public Optional<MessageDTO.GetMessageDto> findById(Long id) {
        return messageRepository.findById(id)
                .map(messageMapper::toGetMessageDto);
    }

    @Override
    public List<MessageDTO.GetMessageDto> findAll() {
        return messageRepository.findAll().stream()
                .map(messageMapper::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MessageDTO.GetMessageDto> findAll(Pageable pageable) {
        return messageRepository.findAll(pageable)
                .map(messageMapper::toGetMessageDto);
    }

    @Override
    public void deleteById(Long id) {
        messageRepository.deleteById(id);
    }

    @Override
    public List<MessageDTO.GetMessageDto> findBySenderId(Long senderId) {
        return messageRepository.findBySenderId(senderId).stream()
                .map(messageMapper::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO.GetMessageDto> findByReceiverId(Long receiverId) {
        return messageRepository.findByReceiverId(receiverId).stream()
                .map(messageMapper::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO.GetMessageDto> findConversation(Long user1Id, Long user2Id) {
        // Cần tìm cả tin nhắn từ user1 gửi cho user2 và ngược lại
        List<Message> sent = messageRepository.findBySenderIdAndReceiverId(user1Id, user2Id);
        List<Message> received = messageRepository.findBySenderIdAndReceiverId(user2Id, user1Id);

        List<Message> conversation = sent;
        conversation.addAll(received);

        conversation.sort((m1, m2) -> m1.getCreatedAt().compareTo(m2.getCreatedAt()));

        return conversation.stream()
                .map(messageMapper::toGetMessageDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageDTO.GetMessageDto> findConversationPaged(Long user1Id, Long user2Id, Pageable pageable) {

        return findConversation(user1Id, user2Id).stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }
}