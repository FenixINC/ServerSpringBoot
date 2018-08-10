package com.server.server.repository;

import com.server.server.entity.Remind;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemindRepository extends JpaRepository<Remind, Long> {

    List<Remind> getAllByTypeRemind(String type);
}