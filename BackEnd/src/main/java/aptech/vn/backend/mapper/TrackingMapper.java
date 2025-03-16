package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.TrackingDTO;
import aptech.vn.backend.entity.Tracking;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrackingMapper {
    TrackingDTO.GetTrackingDto toTrackingDto(Tracking entity);
    Tracking toTrackingEntity(TrackingDTO.SaveTrackingDto dto);
}
