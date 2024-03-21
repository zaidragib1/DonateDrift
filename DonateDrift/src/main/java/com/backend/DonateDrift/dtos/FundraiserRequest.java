package com.backend.DonateDrift.dtos;

import com.backend.DonateDrift.entity.Attachment;
import com.backend.DonateDrift.enums.Category;
import com.backend.DonateDrift.enums.FundraiserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FundraiserRequest{

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
    private MultipartFile coverPhoto;
    private List<MultipartFile> files = new ArrayList<>();
}
