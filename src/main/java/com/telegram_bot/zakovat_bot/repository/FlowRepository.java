/**
 * Author: Shaxzod Ro'ziqulov
 * User:Ruzikulov
 * DATE:09.07.2024
 * TIME:12:16
 */
package com.telegram_bot.zakovat_bot.repository;

import com.telegram_bot.zakovat_bot.entity.Flow;
import com.telegram_bot.zakovat_bot.entity.enumerated.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlowRepository extends JpaRepository<Flow,Long> {
    @Modifying
    @Query("UPDATE flow f SET f.status = :oldStatus WHERE f.userId = :userId AND f.status = :newStatus")
    void updateFlowStatus(@Param("newStatus") Status newStatus, @Param("userId") Long userId, @Param("oldStatus") Status oldStatus);
    List<Flow> findByUserIdAndStatus(Long userId, Status status);
}
