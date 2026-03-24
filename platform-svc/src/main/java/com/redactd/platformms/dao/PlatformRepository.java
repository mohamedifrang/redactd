package com.redactd.platformms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.redactd.platformms.entity.PlatformEntity;

public interface PlatformRepository extends JpaRepository<PlatformEntity,Long> {

    @Modifying
    @Query("""
        UPDATE PlatformEntity c 
        SET c.scoreSum = c.scoreSum + :deltaScore,
        c.activeContentCount = c.activeContentCount + :deltaCount,
        c.averageModerationScore = CASE WHEN (c.activeContentCount + :deltaCount) = 0 THEN 0
                          ELSE (c.scoreSum + :deltaScore) / (c.activeContentCount + :deltaCount)
                     END
        WHERE c.id = :platformId
    """)
    int updateRatingAtomically(@Param("platformId") Long platformId,
        @Param("deltaScore") double deltaScore,
        @Param("deltaCount") int deltaCount);

}






