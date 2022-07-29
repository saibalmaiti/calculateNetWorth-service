package com.cts.networth.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Builder
//@ApiModel(value = "Model object that stores the PortFolio Details.")
@Entity
@Table(name = "portfoliodetails")
public class PortfolioDetail {

    //@ApiModelProperty(notes = "id of the portfolio")
    @Id
    private int pid ;

    //@ApiModelProperty(notes = " stock list")
    @OneToMany(mappedBy="portfolioDetail", fetch = FetchType.LAZY)
    private List<StockDetail> stockDetailList;

    //@ApiModelProperty(notes = " mutual fund  list")
    @OneToMany(mappedBy = "portfolioDetail", fetch = FetchType.LAZY)
    private  List<MutualFundDetail> mutualFundDetails;
}
