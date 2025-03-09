package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.OrderDetailDTO;
import aptech.vn.backend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailMapper {
    OrderDetailDTO toDto(OrderDetail entity);
    OrderDetail toEntity(OrderDetailDTO dto);
}