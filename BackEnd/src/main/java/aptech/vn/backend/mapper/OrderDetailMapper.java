package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "medicineId", source = "medicine.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    OrderDetailDTO.GetDto toGetDto(OrderDetail entity);

    OrderDetail toEntity(OrderDetailDTO.SaveDto dto);
}