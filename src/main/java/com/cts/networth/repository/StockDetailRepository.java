package com.cts.networth.repository;

import com.cts.networth.model.PortfolioDetail;
import com.cts.networth.model.StockDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDetailRepository extends JpaRepository<StockDetail,Integer> {
}
