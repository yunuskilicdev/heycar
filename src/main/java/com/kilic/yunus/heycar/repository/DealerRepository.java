package com.kilic.yunus.heycar.repository;

import com.kilic.yunus.heycar.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Yunus Kilic
 */
@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
