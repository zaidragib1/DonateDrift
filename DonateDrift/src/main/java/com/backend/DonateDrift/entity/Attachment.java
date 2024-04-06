//package com.backend.DonateDrift.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import jakarta.persistence.*;
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class Attachment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String url;
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="fundraiser_id")
//    private Fundraiser fundraiser;
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="status_id")
//    private Status status;
//}
package com.backend.DonateDrift.entity;

import com.backend.DonateDrift.entity.Fundraiser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="fundraiser_id")
    private Fundraiser fundraiser;
}
