package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.VouchersDTO;
import aptech.vn.backend.entity.Vouchers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VouchersMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    VouchersDTO.GetVouchersDto toGetDto(Vouchers entity);

    Vouchers toEntity(VouchersDTO.SaveVouchersDto dto);
}