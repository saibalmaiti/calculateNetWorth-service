package com.cts.networth.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MutualFundDetails {
    private int mutualFundId;
    private String mutualFundName;
    private double mutualFundValue;
}
