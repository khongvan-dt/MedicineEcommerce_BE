package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoucherMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    VoucherDTO.GetDto toGetDto(Voucher entity);

    Voucher toEntity(VoucherDTO.SaveDto dto);
}