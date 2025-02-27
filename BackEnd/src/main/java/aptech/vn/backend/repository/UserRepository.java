package aptech.vn.backend.repository;

import aptech.vn.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
    List<User> findByFullNameContaining(String fullName);
    List<User> findByEnabled(Boolean enabled);
    List<User> findByLocked(Boolean locked);
    List<User> findByLastLoginBetween(LocalDateTime start, LocalDateTime end);
    List<User> findByCountLockGreaterThan(Integer threshold);
    List<User> findByAddressContaining(String address);
}