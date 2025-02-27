package aptech.vn.backend.service.impl;

import aptech.vn.backend.entity.User;
import aptech.vn.backend.repository.UserRepository;
import aptech.vn.backend.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    @Override
    public List<User> findByEnabled(Boolean enabled) {
        return userRepository.findByEnabled(enabled);
    }

    @Override
    public List<User> findByLastLoginBetween(LocalDateTime start, LocalDateTime end) {
        return userRepository.findByLastLoginBetween(start, end);
    }

    @Override
    public boolean lockUser(Long userId) {
        return false;
    }

    @Override
    public boolean unlockUser(Long userId) {
        return false;
    }

    @Override
    public boolean resetPassword(Long userId, String newPassword) {
        return false;
    }

    @Override
    public boolean incrementLockCount(Long userId) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
