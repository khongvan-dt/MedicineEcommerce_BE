package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.UserDTO;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.exception.ResourceNotFoundException;
import aptech.vn.backend.mapper.UserMapper;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.security.PasswordEncoder;
import aptech.vn.backend.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO.GetUserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO.GetUserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toGetUserDto);
    }

    @Override
    @Transactional
    public UserDTO.GetUserDto saveOrUpdate(UserDTO.SaveUserDto userDTO) {
        // Validate email uniqueness when creating a new user
        if (userDTO.getId() == null && userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + userDTO.getEmail());
        }

        User user;

        if (userDTO.getId() == null) {
            // INSERT case
            user = new User();
            user.setCreatedAt(LocalDateTime.now());

            // Mật khẩu là bắt buộc cho người dùng mới
            if (userDTO.getPassword() == null || userDTO.getPassword().isEmpty()) {
                throw new IllegalArgumentException("Password is required for new users");
            }
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            // UPDATE case
            user = userRepository.findById(userDTO.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userDTO.getId()));

            // Kiểm tra nếu email đã thay đổi và đã tồn tại
            if (!user.getEmail().equals(userDTO.getEmail()) &&
                    userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exists: " + userDTO.getEmail());
            }

            // Chỉ cập nhật mật khẩu nếu được cung cấp
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
        }

        user.setUpdatedAt(LocalDateTime.now());
        user.setFullName(userDTO.getFullName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getEmail());
        user.setEnabled(userDTO.getEnabled() != null ? userDTO.getEnabled() : true);
        user.setLocked(userDTO.getLocked() != null ? userDTO.getLocked() : false);
        user.setCountLock(userDTO.getCountLock() != null ? userDTO.getCountLock() : 0);

        // Thêm cập nhật trường avatar
        if (userDTO.getAvatar() != null && !userDTO.getAvatar().trim().isEmpty()) {
            user.setAvatar(userDTO.getAvatar());
        } else if (user.getAvatar() == null) {
            // Nếu avatar không được cung cấp và user chưa có avatar, sử dụng giá trị mặc định
            user.setAvatar("default-avatar.png");
        }
        // Giữ nguyên giá trị avatar hiện tại nếu không có giá trị mới và đã có giá trị cũ

        User savedUser = userRepository.save(user);
        return userMapper.toGetUserDto(savedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Cannot delete. User not found with ID: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserDTO.GetUserDto> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toGetUserDto);
    }

    @Override
    public List<UserDTO.GetUserDto> findByFullNameContaining(String fullName) {
        return userRepository.findByFullNameContaining(fullName)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByPhone(String phone) {
        return userRepository.findByPhone(phone)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByAddressContaining(String address) {
        return userRepository.findByAddressContaining(address)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByEnabled(Boolean enabled) {
        return userRepository.findByEnabled(enabled)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByLocked(Boolean locked) {
        return userRepository.findByLocked(locked)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByLastLoginAfter(LocalDateTime date) {
        return userRepository.findByLastLoginAfter(date)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO.GetUserDto> findByCountLockGreaterThanEqual(Integer count) {
        return userRepository.findByCountLockGreaterThanEqual(count)
                .stream()
                .map(userMapper::toGetUserDto)
                .collect(Collectors.toList());
    }
}