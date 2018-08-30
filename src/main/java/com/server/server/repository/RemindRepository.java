package com.server.server.repository;

import com.server.server.entity.Remind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RemindRepository extends JpaRepository<Remind, Long> {

    @Query(value = "SELECT remind FROM Remind remind WHERE typeRemind = :typeRemind")
    List<Remind> getAllByType(String typeRemind);

    @Query("SELECT remind FROM Remind remind WHERE remind.title = :title OR remind.description = :description")
    Remind isRemindExists(
            @Param("title") String title,
            @Param("description") String description
    );
}