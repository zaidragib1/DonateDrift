package com.backend.DonateDrift.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequest {

    private String description;
    private String photoUrl;
}
