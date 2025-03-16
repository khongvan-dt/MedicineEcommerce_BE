package aptech.vn.backend.mapper;

import aptech.vn.backend.dto.CategoryDTO;
import aptech.vn.backend.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(target = "parentId", source = "parent.id")
    CategoryDTO.GetCategoryDto toGetCategoryDto(Category entity);

    Category toEntity(CategoryDTO.SaveCategoryDto dto);
}