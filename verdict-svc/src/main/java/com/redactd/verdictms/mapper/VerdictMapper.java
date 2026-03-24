package com.redactd.verdictms.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.redactd.verdictms.bean.Verdict;
import com.redactd.verdictms.entity.VerdictEntity;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VerdictMapper {
    Verdict toBean(VerdictEntity VerdictEntity);
    VerdictEntity toEntity(Verdict Verdict);
    List<Verdict> toBeanList(List<VerdictEntity> reviewEntities);
    @Mapping(target = "id",ignore = true)
    void updateEntityFromBean(Verdict Verdict, @MappingTarget VerdictEntity VerdictEntity);
}






