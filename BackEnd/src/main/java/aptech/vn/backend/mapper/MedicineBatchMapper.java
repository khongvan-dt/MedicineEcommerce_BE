package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.MedicineBatchDTO;
import aptech.vn.backend.entity.MedicineBatch;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineBatchMapper {
    MedicineBatchDTO toDto(MedicineBatch entity);
    MedicineBatch toEntity(MedicineBatchDTO dto);
}
