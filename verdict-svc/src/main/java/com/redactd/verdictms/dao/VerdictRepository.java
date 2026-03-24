package com.redactd.verdictms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redactd.verdictms.entity.VerdictEntity;

public interface VerdictRepository extends JpaRepository<VerdictEntity,Long> {

    List<VerdictEntity> findByPlatformId(Long platformId);

}






