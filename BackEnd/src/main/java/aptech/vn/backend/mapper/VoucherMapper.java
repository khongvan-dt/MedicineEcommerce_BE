package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.VoucherDTO;
import aptech.vn.backend.entity.Voucher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoucherMapper {
    VoucherDTO toDto(Voucher entity);
    Voucher toEntity(VoucherDTO dto);
}
