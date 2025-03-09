package aptech.vn.backend.service;

import aptech.vn.backend.dto.UserRoleDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRoleService {
    List<UserRoleDTO> findAll();
    Optional<UserRoleDTO> findById(Long id);
    UserRoleDTO save(UserRoleDTO userRoleDTO);
    void deleteById(Long id);
    List<UserRoleDTO> findByUserId(Long userId);
    List<UserRoleDTO> findByRoleId(Long roleId);
    Optional<UserRoleDTO> findByUserIdAndRoleId(Long userId, Long roleId);
}