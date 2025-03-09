package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    ReviewDTO toDto(Review entity);
    Review toEntity(ReviewDTO dto);
}
