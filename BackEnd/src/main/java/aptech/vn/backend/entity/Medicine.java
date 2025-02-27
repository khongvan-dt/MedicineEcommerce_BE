package aptech.vn.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Medicine extends BaseEntity {
    private String code;
    private String name;
    @ManyToOne
    private Brand brand;
    private String origin;
    private String manufacturer;
}