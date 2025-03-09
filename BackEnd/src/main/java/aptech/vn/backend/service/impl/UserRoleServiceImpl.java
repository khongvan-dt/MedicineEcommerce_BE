package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.entity.UserRole;
import aptech.vn.backend.mapper.UserRoleMapper;
import aptech.vn.backend.repository.UserRoleRepository;
import aptech.vn.backend.service.UserRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository, UserRoleMapper userRoleMapper) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<UserRoleDTO> findAll() {
        return userRoleRepository.findAll()
                .stream()
                .map(userRoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO> findById(Long id) {
        return userRoleRepository.findById(id).map(userRoleMapper::toDto);
    }

    @Override
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        UserRole userRole = userRoleMapper.toEntity(userRoleDTO);
        userRoleRepository.save(userRole);
        return userRoleMapper.toDto(userRole);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public List<UserRoleDTO> findByUserId(Long userId) {
        return userRoleRepository.findByUser_Id(userId)
                .stream()
                .map(userRoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleDTO> findByRoleId(Long roleId) {
        return userRoleRepository.findByRole_Id(roleId)
                .stream()
                .map(userRoleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO> findByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleRepository.findByUser_IdAndRole_Id(userId, roleId).map(userRoleMapper::toDto);
    }
}
