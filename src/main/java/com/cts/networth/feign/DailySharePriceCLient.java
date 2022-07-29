package com.cts.networth.feign;

import java.util.List;

import com.cts.networth.dto.StockDetails;
import com.cts.networth.model.StockDetail;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "dailySharePrice-service", url = "${shareprice.URL}")
public interface DailySharePriceCLient {

    @GetMapping("/dailySharePrice/{stockName}")
    public StockDetails getDailySharePrice(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
                                           @ApiParam(name = "stockName", value = "name of the stock") @PathVariable  String stockName) throws Exception;

}
