package aptech.vn.backend.security;

/**
 * Interface định nghĩa các phương thức mã hóa mật khẩu
 */
public interface PasswordEncoder {

    /**
     * Mã hóa mật khẩu người dùng
     *
     * @param rawPassword Mật khẩu chưa mã hóa
     * @return Mật khẩu đã được mã hóa
     */
    String encode(String rawPassword);

    /**
     * So sánh mật khẩu nhập vào với mật khẩu đã mã hóa
     *
     * @param rawPassword Mật khẩu chưa mã hóa
     * @param encodedPassword Mật khẩu đã mã hóa
     * @return true nếu mật khẩu khớp, false nếu không khớp
     */
    boolean matches(String rawPassword, String encodedPassword);
}