package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalaryMapper {
    SalaryDTO toDto(Salary entity);
    Salary toEntity(SalaryDTO dto);
}
