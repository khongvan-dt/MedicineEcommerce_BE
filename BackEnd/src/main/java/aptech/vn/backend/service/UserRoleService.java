package aptech.vn.backend.service;

import aptech.vn.backend.dto.UserRoleDTO;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {
    List<UserRoleDTO.GetDto> findAll();
    Optional<UserRoleDTO.GetDto> findById(Long id);
    UserRoleDTO.GetDto saveOrUpdate(UserRoleDTO.SaveDto userRoleDTO);
    void deleteById(Long id);
    List<UserRoleDTO.GetDto> findByUserId(Long userId);
    List<UserRoleDTO.GetDto> findByRoleId(Long roleId);
    Optional<UserRoleDTO.GetDto> findByUserIdAndRoleId(Long userId, Long roleId);
}