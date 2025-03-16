package aptech.vn.backend.security;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

/**
 * Triển khai PasswordEncoder sử dụng thuật toán BCrypt
 */
@Component
public class BCryptPasswordEncoder implements PasswordEncoder {

    private final int logRounds;

    /**
     * Constructor mặc định với số vòng lặp = 10 (khuyến nghị cho hầu hết trường hợp)
     */
    public BCryptPasswordEncoder() {
        this(10);
    }

    /**
     * Constructor cho phép tùy chỉnh số vòng lặp
     * @param logRounds Số vòng lặp log2 (thường từ 10-12 cho môi trường sản xuất)
     */
    public BCryptPasswordEncoder(int logRounds) {
        if (logRounds < 4 || logRounds > 31) {
            throw new IllegalArgumentException("Số vòng lặp phải từ 4 đến 31");
        }
        this.logRounds = logRounds;
    }

    /**
     * Mã hóa mật khẩu sử dụng BCrypt với salt ngẫu nhiên
     */
    @Override
    public String encode(String rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("Mật khẩu không được null");
        }
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(logRounds));
    }

    /**
     * Kiểm tra mật khẩu gốc với mật khẩu đã được mã hóa
     */
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        if (rawPassword == null || encodedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(rawPassword, encodedPassword);
        } catch (IllegalArgumentException e) {
            return false; // Không phải định dạng hash BCrypt hợp lệ
        }
    }
}