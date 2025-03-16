package aptech.vn.backend.service;

import aptech.vn.backend.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO.GetRoleDto> findAll();
    Optional<RoleDTO.GetRoleDto> findById(Long id);
    RoleDTO.GetRoleDto saveOrUpdate(RoleDTO.SaveRoleDto roleDTO);
    void deleteById(Long id);
    Optional<RoleDTO.GetRoleDto> findByName(String name);
}