package aptech.vn.backend.config;

import aptech.vn.backend.security.BCryptPasswordEncoder;
import aptech.vn.backend.security.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    /**
     * Cấu hình PasswordEncoder để có thể tiêm vào các service
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Cấu hình với 12 vòng lặp log - an toàn hơn cho môi trường sản xuất
        // Nhưng sẽ tốn nhiều CPU hơn
        return new BCryptPasswordEncoder(12);

        // Hoặc sử dụng mức mặc định (10 vòng lặp log)
        // return new BCryptPasswordEncoder();
    }
}