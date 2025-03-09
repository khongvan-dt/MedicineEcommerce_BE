package aptech.vn.backend.service;

import aptech.vn.backend.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO> findAll();
    Optional<UserDTO> findById(Long id);
    UserDTO save(UserDTO userDTO);
    void deleteById(Long id);
    Optional<UserDTO> findByEmail(String email);
    List<UserDTO> findByFullNameContaining(String fullName);
    List<UserDTO> findByPhone(String phone);
    List<UserDTO> findByAddressContaining(String address);
    List<UserDTO> findByEnabled(Boolean enabled);
    List<UserDTO> findByLocked(Boolean locked);
    List<UserDTO> findByLastLoginAfter(LocalDateTime date);
    List<UserDTO> findByCountLockGreaterThanEqual(Integer count);
}