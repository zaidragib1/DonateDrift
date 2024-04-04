package com.backend.DonateDrift.repository;


import com.backend.DonateDrift.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonorHistoryRepository extends JpaRepository<Donor,Long> {
    List<Donor> findByUserId(Long userId);
}
