package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.SalaryDTO;
import aptech.vn.backend.entity.Salary;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalaryMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    SalaryDTO.GetDto toGetDto(Salary entity);

    Salary toEntity(SalaryDTO.SaveDto dto);
}