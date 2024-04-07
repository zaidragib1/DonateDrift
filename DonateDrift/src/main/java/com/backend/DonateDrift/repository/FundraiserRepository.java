package com.backend.DonateDrift.repository;

import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.enums.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("select f from Fundraiser f where f.user.id=:id")
    public List<Fundraiser> getAllFundraiserById(@Param("id") long id);
    
    @Query("SELECT f FROM Fundraiser f WHERE f.country = :country")
    List<Fundraiser> findByCountry(@Param("country") String country);

    @Query("SELECT f FROM Fundraiser f WHERE f.city = :city")
    List<Fundraiser> findByCity(@Param("city") String city);

    @Query("SELECT f FROM Fundraiser f WHERE f.category = :category")
    List<Fundraiser> findByCategory(@Param("category") Category category);
    
    @Query("select f from Fundraiser f where f.id=:id")
    public Fundraiser getAllById(@Param("id") long id);

}
