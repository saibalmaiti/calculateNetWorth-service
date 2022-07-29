package com.cts.networth.dto;

import com.cts.networth.model.PortfolioDetail;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellAsset {
    private PortfolioDetail currDetails;
    private PortfolioDetail saleDetails;
}
