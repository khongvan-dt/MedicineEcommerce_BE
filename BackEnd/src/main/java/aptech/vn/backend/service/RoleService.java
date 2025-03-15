package aptech.vn.backend.service;

import aptech.vn.backend.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO.GetDto> findAll();
    Optional<RoleDTO.GetDto> findById(Long id);
    RoleDTO.GetDto saveOrUpdate(RoleDTO.SaveDto roleDTO);
    void deleteById(Long id);
    Optional<RoleDTO.GetDto> findByName(String name);
}