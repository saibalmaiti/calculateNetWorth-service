package com.cts.networth.model;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Model object that stores the mutual fund  sell response.")
public class AssetSaleResponse {

    @ApiModelProperty(notes = " sale status")
    @NotNull(message = " status cannot be empty")
    private boolean saleStatus;

    @ApiModelProperty(notes = " net worth")
    @NotNull(message = " networth cannot be empty")
    private  double netWorth;

}
