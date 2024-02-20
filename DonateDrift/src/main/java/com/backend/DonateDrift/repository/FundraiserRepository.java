package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Fundraiser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser, Long> {

    @Query("SELECT f FROM Fundraiser f where f.fundraiserStatus=com.backend.DonateDrift.enums.FundraiserStatus.ACCEPTED")
    public List<Fundraiser> getAllAccepted();

    @Query("SELECT f FROM Fundraiser f where f.fundraiserStatus=com.backend.DonateDrift.enums.FundraiserStatus.REJECTED")
    public List<Fundraiser> getAllRejected();

    @Query("SELECT f FROM Fundraiser f where f.fundraiserStatus=com.backend.DonateDrift.enums.FundraiserStatus.PENDING")
    public List<Fundraiser> getAllPending();

}
