package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.ReviewDTO;
import aptech.vn.backend.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "medicineId", source = "medicine.id")
    @Mapping(target = "serviceId", source = "service.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ReviewDTO.GetReviewDto toGetReviewDto(Review entity);

    Review toReviewEntity(ReviewDTO.SaveReviewDto dto);
}