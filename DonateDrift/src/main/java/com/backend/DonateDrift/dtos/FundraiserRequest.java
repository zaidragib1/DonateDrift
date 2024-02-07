package com.backend.DonateDrift.dtos;

import com.backend.DonateDrift.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class FundraiserRequest {

    private String title;
    private Category category;
    private String country;
    private String city;
    private String firstName;
    private String lastName;
    private String description;
    private String videoUrl;
    private String upiId;
    private long requiredAmount;
    private String attachmentUrl;
    //private LocalDateTime createdAt;

}
