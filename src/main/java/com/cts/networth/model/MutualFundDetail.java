package com.cts.networth.model;



import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
//@ApiModel(value = "Model object that stores the mutual fund  Details.")
@Entity
@Table(name = "mutualfunddetails")
public class MutualFundDetail {

    @Id
    //@ApiModelProperty(notes = " mutual fund id")
    private  int mid;

    //@ApiModelProperty(notes = " mutual fund name")
    @NotNull(message = "mutual fund name is mandatory")
    private String mutualFundName;

//    @ApiModelProperty(notes = " mutual fund units ")
    @Min(value = 0, message = "Minimum cost is 1")
    private  int mutualFundUnits;

    @ManyToOne
    @JoinColumn(name = "pid")
    @JsonIgnore
    private PortfolioDetail portfolioDetail;
}
