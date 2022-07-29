package com.cts.networth.repository;

import com.cts.networth.model.MutualFundDetail;
import com.cts.networth.model.PortfolioDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutualFundDetailRepository extends JpaRepository<MutualFundDetail,Integer> {
}
