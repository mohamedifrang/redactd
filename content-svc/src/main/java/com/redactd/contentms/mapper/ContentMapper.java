package com.redactd.contentms.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.redactd.contentms.bean.Content;
import com.redactd.contentms.entity.ContentEntity;

@Mapper(componentModel = "spring"
    ,nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContentMapper {
    ContentEntity toEntity(Content content);
    Content toBean(ContentEntity ContentEntity);
    List<Content> toBeanList(List<ContentEntity> contentEntities);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "platformId",ignore = true)
    void updateEntityFromBean(Content content,@MappingTarget ContentEntity ContentEntity);
}






