package com.cts.networth.repository;

import com.cts.networth.model.PortfolioDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioDetailRepository extends JpaRepository<PortfolioDetail,Integer> {
}
