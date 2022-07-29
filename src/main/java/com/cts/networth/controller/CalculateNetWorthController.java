package com.cts.networth.controller;


import com.cts.networth.dto.SellAsset;
import com.cts.networth.exception.AuthorizationException;
import com.cts.networth.feign.AuthorizationClient;

import com.cts.networth.model.AssetSaleResponse;
import com.cts.networth.model.PortfolioDetail;
import com.cts.networth.service.CalculateNetWorthService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class CalculateNetWorthController {


    @Autowired
    private CalculateNetWorthService calculateNetWorthService;

    @Autowired
    private AuthorizationClient client;



    @GetMapping("/getPortfolioDetail/{pid}")
    public PortfolioDetail getPortfolioDetail(@PathVariable int pid, @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws  Exception
    {
        System.out.println("Inside get by id............");
        if (client.authorizeTheRequest(requestTokenHeader)) {
            return calculateNetWorthService.getPortfolioDetailById(requestTokenHeader,pid);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }


    @PostMapping("/calculateNetWorth")
    public double calculateNetWorth(
            @RequestBody PortfolioDetail portfolioDetail,
            @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
        System.out.println("Inside calculate net worth............");
        double net=0.0;
        if (client.authorizeTheRequest(requestTokenHeader)) {
           net=calculateNetWorthService.calculateNetWorth(requestTokenHeader,portfolioDetail);
           return net;
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }

    @PostMapping("/sellAssets")
    public ResponseEntity<AssetSaleResponse> sellAssets(
            @RequestBody SellAsset sellAsset,
            @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
        System.out.println("Inside sell assets.............");
        if (client.authorizeTheRequest(requestTokenHeader)) {
            return calculateNetWorthService.sellAssets(requestTokenHeader,sellAsset);
        } else {
            throw new AuthorizationException("Not allowed");
        }
    }
}
