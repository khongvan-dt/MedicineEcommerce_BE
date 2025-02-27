package aptech.vn.backend.service;

import aptech.vn.backend.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role save(Role role);
    Optional<Role> findById(Long id);
    List<Role> findAll();
    Page<Role> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<Role> findByName(String name);
}