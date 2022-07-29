package com.cts.networth.service;

import com.cts.networth.dto.SellAsset;
import com.cts.networth.exception.PortfolioDetailNotFoundException;
import com.cts.networth.feign.DailyMutualFundNavClient;
import com.cts.networth.feign.DailySharePriceCLient;
import com.cts.networth.model.AssetSaleResponse;
import com.cts.networth.model.MutualFundDetail;
import com.cts.networth.model.PortfolioDetail;
import com.cts.networth.model.StockDetail;
import com.cts.networth.repository.MutualFundDetailRepository;
import com.cts.networth.repository.PortfolioDetailRepository;
import com.cts.networth.repository.StockDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CalculateNetWorthServiceImpl implements  CalculateNetWorthService{

    @Autowired
    private DailySharePriceCLient dailySharePriceCLient;

    @Autowired
    private DailyMutualFundNavClient dailyMutualFundNavCLient;

    @Autowired
    private PortfolioDetailRepository portfolioDetailRepository;
    @Autowired
    private StockDetailRepository stockDetailRepository;
    @Autowired
    private MutualFundDetailRepository mutualFundDetailRepository;


    @Override
    public PortfolioDetail getPortfolioDetailById(String token, int id) throws PortfolioDetailNotFoundException {

        Optional<PortfolioDetail> p=portfolioDetailRepository.findById(id);
        if(!(p.isPresent()))
        {
            throw new PortfolioDetailNotFoundException("There is no such Portfolio ");
        }
        else{
            return p.get();
        }

    }


    @Override
    public double calculateNetWorth(String token, PortfolioDetail portfolioDetail) throws Exception {

        List<StockDetail> stockDetailList =portfolioDetail.getStockDetailList();
        List<MutualFundDetail> mutualFundDetails=portfolioDetail.getMutualFundDetails();

        double stockValue=0.0,mutualFundValue=0.0,netWorth=0.0;

        for(StockDetail stock : stockDetailList)
        {
            stockValue+=dailySharePriceCLient.getDailySharePrice(token,stock.getStockName()).getStockValue()*stock.getStockCount();
        }

        for(MutualFundDetail mutual : mutualFundDetails) {
            mutualFundValue+=dailyMutualFundNavCLient.getDailyNavValue(token, mutual.getMutualFundName()).getMutualFundValue()*mutual.getMutualFundUnits();
        }

        //net worth
        netWorth=stockValue+mutualFundValue;

        return netWorth;
    }



    @Override
    public ResponseEntity<AssetSaleResponse> sellAssets(String token, SellAsset sellAsset) throws Exception {
        PortfolioDetail saleDetails = sellAsset.getSaleDetails();
        PortfolioDetail currDetails = sellAsset.getCurrDetails();

        List<StockDetail> saleStock = saleDetails.getStockDetailList();
        List<StockDetail> currStock = currDetails.getStockDetailList();

        List<MutualFundDetail> saleFund = saleDetails.getMutualFundDetails();
        List<MutualFundDetail> currFund = currDetails.getMutualFundDetails();
        //if no selling item found
        if(saleStock.isEmpty() && saleFund.isEmpty()) {
            double net=calculateNetWorth(token,currDetails);
            AssetSaleResponse assetSaleResponse=new AssetSaleResponse();
            assetSaleResponse.setNetWorth(net);
            assetSaleResponse.setSaleStatus(false);
            return new ResponseEntity<>(assetSaleResponse, HttpStatus.OK);
        }
        for(StockDetail sStock: saleStock) {
            for(StockDetail cStock: currStock) {
                if(sStock.getStockName().equals(cStock.getStockName())){
                    //if sell stock count is more than curr, calculate net and return with sale status false
                    if(sStock.getStockCount() > cStock.getStockCount()){

                        double net=calculateNetWorth(token,currDetails);
                        AssetSaleResponse assetSaleResponse=new AssetSaleResponse();
                        assetSaleResponse.setNetWorth(net);
                        assetSaleResponse.setSaleStatus(false);

                        return new ResponseEntity<>(assetSaleResponse, HttpStatus.OK);

                    }
                    //else reduce the curr stock count
                    else{
                        cStock.setStockCount(cStock.getStockCount() - sStock.getStockCount());
                        stockDetailRepository.save(cStock);
                    }
                }
            }
        }

        for(MutualFundDetail sFund: saleFund) {
            for(MutualFundDetail cFund: currFund) {
                if(sFund.getMutualFundName().equals(cFund.getMutualFundName())){
                    //if sell fun count is more than curr, calculate net and return with sale status false
                    if(sFund.getMutualFundUnits() > cFund.getMutualFundUnits()){
                        double net=calculateNetWorth(token,currDetails);
                        AssetSaleResponse assetSaleResponse=new AssetSaleResponse();
                        assetSaleResponse.setNetWorth(net);
                        assetSaleResponse.setSaleStatus(false);
                        return new ResponseEntity<>(assetSaleResponse, HttpStatus.OK);
                    }
                    //else reduce the curr stock count
                    else{
                        cFund.setMutualFundUnits(cFund.getMutualFundUnits() - sFund.getMutualFundUnits());
                        mutualFundDetailRepository.save(cFund);
                    }
                }
            }
        }


        currDetails.setStockDetailList(currStock);
        currDetails.setMutualFundDetails(currFund);
        portfolioDetailRepository.save(currDetails);

        //net worth
        double net=calculateNetWorth(token,currDetails);

        AssetSaleResponse assetSaleResponse=new AssetSaleResponse();
        assetSaleResponse.setNetWorth(net);
        assetSaleResponse.setSaleStatus(true);


        return new ResponseEntity<>(assetSaleResponse, HttpStatus.OK);
    }

}

