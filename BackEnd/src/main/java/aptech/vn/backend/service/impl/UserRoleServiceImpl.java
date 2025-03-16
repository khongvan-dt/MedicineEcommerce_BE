package aptech.vn.backend.service.impl;

import aptech.vn.backend.dto.UserRoleDTO;
import aptech.vn.backend.entity.Role;
import aptech.vn.backend.entity.User;
import aptech.vn.backend.entity.UserRole;
import aptech.vn.backend.mapper.UserRoleMapper;
import aptech.vn.backend.repository.RoleRepository;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.repository.UserRoleRepository;
import aptech.vn.backend.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private final UserRoleMapper userRoleMapper;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserRoleServiceImpl(
            UserRoleRepository userRoleRepository,
            UserRoleMapper userRoleMapper,
            UserRepository userRepository,
            RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.userRoleMapper = userRoleMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserRoleDTO.GetUserRoleDto> findAll() {
        return userRoleRepository.findAll()
                .stream()
                .map(userRoleMapper::toGetUserRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO.GetUserRoleDto> findById(Long id) {
        return userRoleRepository.findById(id)
                .map(userRoleMapper::toGetUserRoleDto);
    }

    @Override
    @Transactional
    public UserRoleDTO.GetUserRoleDto saveOrUpdate(UserRoleDTO.SaveUserRoleDto userRoleDTO) {
        UserRole userRole;

        if (userRoleDTO.getId() == null || userRoleDTO.getId() == 0) {
            // INSERT case
            userRole = new UserRole();
            userRole.setCreatedAt(LocalDateTime.now());
            userRole.setUpdatedAt(LocalDateTime.now());
        } else {
            // UPDATE case
            Optional<UserRole> existingUserRole = userRoleRepository.findById(userRoleDTO.getId());
            if (existingUserRole.isEmpty()) {
                throw new RuntimeException("UserRole not found with ID: " + userRoleDTO.getId());
            }
            userRole = existingUserRole.get();
            userRole.setUpdatedAt(LocalDateTime.now());
        }

        // Xử lý user relationship
        User user = userRepository.findById(userRoleDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userRoleDTO.getUserId()));
        userRole.setUser(user);

        // Xử lý role relationship
        Role role = roleRepository.findById(userRoleDTO.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with ID: " + userRoleDTO.getRoleId()));
        userRole.setRole(role);

        UserRole savedUserRole = userRoleRepository.save(userRole);
        return userRoleMapper.toGetUserRoleDto(savedUserRole);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public List<UserRoleDTO.GetUserRoleDto> findByUserId(Long userId) {
        return userRoleRepository.findByUser_Id(userId)
                .stream()
                .map(userRoleMapper::toGetUserRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRoleDTO.GetUserRoleDto> findByRoleId(Long roleId) {
        return userRoleRepository.findByRole_Id(roleId)
                .stream()
                .map(userRoleMapper::toGetUserRoleDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserRoleDTO.GetUserRoleDto> findByUserIdAndRoleId(Long userId, Long roleId) {
        return userRoleRepository.findByUser_IdAndRole_Id(userId, roleId)
                .map(userRoleMapper::toGetUserRoleDto);
    }
}