package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.RoleDTO;
import aptech.vn.backend.entity.Role;
import aptech.vn.backend.mapper.RoleMapper;
import aptech.vn.backend.repository.RoleRepository;
import aptech.vn.backend.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        return roleRepository.findById(id).map(roleMapper::toDto);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        role = roleRepository.save(role);
        return roleMapper.toDto(role);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<RoleDTO> findByName(String name) {
        return roleRepository.findByName(name).map(roleMapper::toDto);
    }
}
