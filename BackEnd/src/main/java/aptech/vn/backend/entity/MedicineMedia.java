package aptech.vn.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medicine_medias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class MedicineMedia extends BaseEntity {
    @ManyToOne
    private Medicine medicine;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    private String mediaUrl;
    private Boolean mainImage;
}
