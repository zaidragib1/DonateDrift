package com.backend.DonateDrift.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorRequest {

    private String name;
    private long amount;
    private String comment;

}
