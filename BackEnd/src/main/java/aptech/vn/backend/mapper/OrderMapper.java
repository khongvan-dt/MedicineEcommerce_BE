package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    OrderDTO.GetDto toGetDto(Order entity);

    Order toEntity(OrderDTO.SaveDto dto);
}