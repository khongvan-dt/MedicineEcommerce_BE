package aptech.vn.backend.service;

import aptech.vn.backend.dto.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> findAll();
    Optional<RoleDTO> findById(Long id);
    RoleDTO save(RoleDTO roleDTO);
    void deleteById(Long id);
    Optional<RoleDTO> findByName(String name);
}