package com.kilic.yunus.heycar.repository;

import com.kilic.yunus.heycar.model.Dealer;
import com.kilic.yunus.heycar.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Yunus Kilic
 */
@Repository
public interface ListingRepository extends JpaRepository<Listing, Long>, JpaSpecificationExecutor<Listing> {

    Optional<Listing> findByCodeAndDealer(String code, Dealer dealer);

    void deleteAllByCodeInAndDealer(List<String> codes, Dealer dealer);
}
