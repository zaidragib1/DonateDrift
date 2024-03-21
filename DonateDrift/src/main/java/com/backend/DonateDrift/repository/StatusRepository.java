package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("select s from Status s where s.fundraiser.id=:fundraiserId")
    public List<Status> findByFundraiserId(@Param("fundraiserId") Long fundraiserId);
}
