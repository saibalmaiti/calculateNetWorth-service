package com.cts.networth.feign;

import com.cts.networth.dto.MutualFundDetails;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "dailyMutualFundNav-service", url = "${nav.URL}")
public interface DailyMutualFundNavClient {

    @GetMapping("/mutualFundNav/{mutualFundName}")
    public MutualFundDetails getDailyNavValue(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
                                              @ApiParam(name = "mutualFundName", value = "name of the mutual fund") @PathVariable String mutualFundName) throws Exception;


}
