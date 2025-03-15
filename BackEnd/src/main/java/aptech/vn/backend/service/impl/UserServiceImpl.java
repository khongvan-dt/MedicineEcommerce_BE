//package aptech.vn.backend.service.impl;
//
//import aptech.vn.backend.dto.UserDTO;
//import aptech.vn.backend.entity.User;
//import aptech.vn.backend.mapper.UserMapper;
//import aptech.vn.backend.repository.UserRepository;
//import aptech.vn.backend.service.UserService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserServiceImpl implements UserService {
//    private final UserMapper userMapper;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
//        this.userMapper = userMapper;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findAll() {
//        return userRepository.findAll()
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<UserDTO.GetDto> findById(Long id) {
//        return userRepository.findById(id)
//                .map(userMapper::toGetDto);
//    }
//
//    @Override
//    @Transactional
//    public UserDTO.GetDto saveOrUpdate(UserDTO.SaveDto userDTO) {
//        User user;
//
//        if (userDTO.getId() == null || userDTO.getId() == 0) {
//            // INSERT case
//            user = new User();
//            user.setCreatedAt(LocalDateTime.now());
//            user.setUpdatedAt(LocalDateTime.now());
//
//            // Mã hóa mật khẩu cho người dùng mới
//            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
//                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            }
//        } else {
//            // UPDATE case
//            Optional<User> existingUser = userRepository.findById(userDTO.getId());
//            if (existingUser.isEmpty()) {
//                throw new RuntimeException("User not found with ID: " + userDTO.getId());
//            }
//            user = existingUser.get();
//            user.setUpdatedAt(LocalDateTime.now());
//
//            // Chỉ cập nhật mật khẩu nếu được cung cấp
//            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
//                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//            }
//        }
//
//        // Cập nhật các trường khác
//        user.setFullName(userDTO.getFullName());
//        user.setPhone(userDTO.getPhone());
//        user.setAddress(userDTO.getAddress());
//        user.setEmail(userDTO.getEmail());
//        user.setEnabled(userDTO.getEnabled());
//        user.setLocked(userDTO.getLocked());
//        user.setCountLock(userDTO.getCountLock());
//
//        User savedUser = userRepository.save(user);
//        return userMapper.toGetDto(savedUser);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<UserDTO.GetDto> findByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .map(userMapper::toGetDto);
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByFullNameContaining(String fullName) {
//        return userRepository.findByFullNameContaining(fullName)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByPhone(String phone) {
//        return userRepository.findByPhone(phone)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByAddressContaining(String address) {
//        return userRepository.findByAddressContaining(address)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByEnabled(Boolean enabled) {
//        return userRepository.findByEnabled(enabled)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByLocked(Boolean locked) {
//        return userRepository.findByLocked(locked)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByLastLoginAfter(LocalDateTime date) {
//        return userRepository.findByLastLoginAfter(date)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO.GetDto> findByCountLockGreaterThanEqual(Integer count) {
//        return userRepository.findByCountLockGreaterThanEqual(count)
//                .stream()
//                .map(userMapper::toGetDto)
//                .collect(Collectors.toList());
//    }
//}