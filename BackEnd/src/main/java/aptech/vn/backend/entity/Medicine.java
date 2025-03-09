package aptech.vn.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Medicine extends BaseEntity {
    @NotBlank
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @Column(name = "origin", nullable = true)
    private String origin;

    @Column(name = "manufacturer", nullable = true)
    private String manufacturer;
}