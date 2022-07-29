package com.cts.networth.model;

import javax.persistence.*;
import javax.sound.sampled.Port;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
//@ApiModel(value = "Model object that stores the stock Details.")
@Entity
@Table(name = "stockdetails")
public class StockDetail {

    @Id
    //@ApiModelProperty(notes = " stock id")
    private int sid;

    //@ApiModelProperty(notes = " stock name")
    @NotNull(message = "stock name is mandatory")
    private String stockName;
    //@ApiModelProperty(notes = " stock holder count")
    @Min(value = 0, message = "Minimum cost is 0")
    private int stockCount;

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonIgnore
    private PortfolioDetail portfolioDetail;

}
