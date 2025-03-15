package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.RoleDTO;
import aptech.vn.backend.entity.Role;
import aptech.vn.backend.mapper.RoleMapper;
import aptech.vn.backend.repository.RoleRepository;
import aptech.vn.backend.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO.GetDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO.GetDto> findById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toGetDto);
    }

    @Override
    @Transactional
    public RoleDTO.GetDto saveOrUpdate(RoleDTO.SaveDto roleDTO) {
        Role role;

        if (roleDTO.getId() == null || roleDTO.getId() == 0) {
            // INSERT case
            role = new Role();
            role.setCreatedAt(LocalDateTime.now());
            role.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<Role> existingRole = roleRepository.findById(roleDTO.getId());
            if (existingRole.isEmpty()) {
                throw new RuntimeException("Role not found with ID: " + roleDTO.getId());
            }
            role = existingRole.get();
            role.setUpdatedAt(LocalDateTime.now());
        }

        // Cập nhật trường name
        role.setName(roleDTO.getName());

        Role savedRole = roleRepository.save(role);
        return roleMapper.toGetDto(savedRole);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<RoleDTO.GetDto> findByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toGetDto);
    }
}