package aptech.vn.backend.service;

import aptech.vn.backend.entity.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    Optional<UserRole> findById(Long id);
    List<UserRole> findAll();
    Page<UserRole> findAll(Pageable pageable);
    void deleteById(Long id);
    List<UserRole> findByUserId(Long userId);
    List<UserRole> findByRoleId(Long roleId);
    boolean assignRoleToUser(Long userId, Long roleId);
    boolean removeRoleFromUser(Long userId, Long roleId);
    Set<String> findRoleNamesByUserId(Long userId);
}
