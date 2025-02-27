package aptech.vn.backend.service;

import aptech.vn.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    void deleteById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByEnabled(Boolean enabled);
    List<User> findByLastLoginBetween(LocalDateTime start, LocalDateTime end);
    boolean lockUser(Long userId);
    boolean unlockUser(Long userId);
    boolean resetPassword(Long userId, String newPassword);
    boolean incrementLockCount(Long userId);
}
