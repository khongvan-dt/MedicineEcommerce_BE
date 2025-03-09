package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.OrderDTO;
import aptech.vn.backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
    OrderDTO toDto(Order entity);
    Order toEntity(OrderDTO dto);
}
