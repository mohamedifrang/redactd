package com.redactd.contentms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redactd.contentms.entity.ContentEntity;

public interface ContentRepository extends JpaRepository<ContentEntity,Long>{

    List<ContentEntity> findByPlatformId(Long platformId);

}






