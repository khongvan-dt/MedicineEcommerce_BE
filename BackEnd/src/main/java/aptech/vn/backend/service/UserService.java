//package aptech.vn.backend.service;
//
//import aptech.vn.backend.dto.UserDTO;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//public interface UserService {
//    List<UserDTO.GetDto> findAll();
//    Optional<UserDTO.GetDto> findById(Long id);
//    UserDTO.GetDto saveOrUpdate(UserDTO.SaveDto userDTO);
//    void deleteById(Long id);
//    Optional<UserDTO.GetDto> findByEmail(String email);
//    List<UserDTO.GetDto> findByFullNameContaining(String fullName);
//    List<UserDTO.GetDto> findByPhone(String phone);
//    List<UserDTO.GetDto> findByAddressContaining(String address);
//    List<UserDTO.GetDto> findByEnabled(Boolean enabled);
//    List<UserDTO.GetDto> findByLocked(Boolean locked);
//    List<UserDTO.GetDto> findByLastLoginAfter(LocalDateTime date);
//    List<UserDTO.GetDto> findByCountLockGreaterThanEqual(Integer count);
//}