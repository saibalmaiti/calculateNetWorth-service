package com.cts.networth.service;

import com.cts.networth.dto.SellAsset;
import com.cts.networth.exception.PortfolioDetailNotFoundException;
import com.cts.networth.model.AssetSaleResponse;
import com.cts.networth.model.PortfolioDetail;
import org.springframework.http.ResponseEntity;

public interface CalculateNetWorthService {

     double calculateNetWorth(String token, PortfolioDetail portfolioDetail) throws Exception;

     PortfolioDetail getPortfolioDetailById(String token, int id) throws PortfolioDetailNotFoundException;
     ResponseEntity<AssetSaleResponse> sellAssets(String token, SellAsset sellAsset) throws Exception;

}
