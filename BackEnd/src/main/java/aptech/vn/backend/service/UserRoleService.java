package aptech.vn.backend.service;

import aptech.vn.backend.dto.UserRoleDTO;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    List<UserRoleDTO.GetUserRoleDto> findAll();
    Optional<UserRoleDTO.GetUserRoleDto> findById(Long id);
    UserRoleDTO.GetUserRoleDto saveOrUpdate(UserRoleDTO.SaveUserRoleDto userRoleDTO);
    void deleteById(Long id);
    List<UserRoleDTO.GetUserRoleDto> findByUserId(Long userId);
    List<UserRoleDTO.GetUserRoleDto> findByRoleId(Long roleId);
    Optional<UserRoleDTO.GetUserRoleDto> findByUserIdAndRoleId(Long userId, Long roleId);
}