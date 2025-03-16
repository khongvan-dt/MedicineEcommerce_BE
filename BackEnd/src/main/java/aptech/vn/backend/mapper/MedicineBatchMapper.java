package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineBatchDTO;
import aptech.vn.backend.entity.MedicineBatch;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineBatchMapper {
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    MedicineBatchDTO.GetMedicineBatchDto toGetMedicineBatchDto(MedicineBatch entity);

    MedicineBatch toMedicineBatchEntity(MedicineBatchDTO.SaveMedicineBatchDto dto);
}