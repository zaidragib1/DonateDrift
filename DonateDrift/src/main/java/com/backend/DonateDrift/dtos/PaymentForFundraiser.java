package com.backend.DonateDrift.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentForFundraiser {
    @JsonProperty("fundraiser_id")
    private Long fundraiserId;
    @JsonProperty("donor_id")
    private Long donorId;
}
