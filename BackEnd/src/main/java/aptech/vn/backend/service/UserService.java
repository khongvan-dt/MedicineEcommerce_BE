package aptech.vn.backend.service;

import aptech.vn.backend.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDTO.GetUserDto> findAll();
    Optional<UserDTO.GetUserDto> findById(Long id);
    UserDTO.GetUserDto saveOrUpdate(UserDTO.SaveUserDto userDTO);
    void deleteById(Long id);
    Optional<UserDTO.GetUserDto> findByEmail(String email);
    List<UserDTO.GetUserDto> findByFullNameContaining(String fullName);
    List<UserDTO.GetUserDto> findByPhone(String phone);
    List<UserDTO.GetUserDto> findByAddressContaining(String address);
    List<UserDTO.GetUserDto> findByEnabled(Boolean enabled);
    List<UserDTO.GetUserDto> findByLocked(Boolean locked);
    List<UserDTO.GetUserDto> findByLastLoginAfter(LocalDateTime date);
    List<UserDTO.GetUserDto> findByCountLockGreaterThanEqual(Integer count);
}