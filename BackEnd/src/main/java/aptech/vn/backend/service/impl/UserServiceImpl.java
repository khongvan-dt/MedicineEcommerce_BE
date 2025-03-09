package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.mapper.UserMapper;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id).map(userMapper::toDto);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDto);
    }

    @Override
    public List<UserDTO> findByFullNameContaining(String fullName) {
        return userRepository.findByFullNameContaining(fullName)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByAddressContaining(String address) {
        return userRepository.findByAddressContaining(address)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByEnabled(Boolean enabled) {
        return userRepository.findByEnabled(enabled)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByLocked(Boolean locked) {
        return userRepository.findByLocked(locked)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByLastLoginAfter(LocalDateTime date) {
        return userRepository.findByLastLoginAfter(date)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findByCountLockGreaterThanEqual(Integer count) {
        return userRepository.findByCountLockGreaterThanEqual(count)
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}