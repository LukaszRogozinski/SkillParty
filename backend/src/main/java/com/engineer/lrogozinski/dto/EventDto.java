package com.engineer.lrogozinski.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EventDto {

    private Integer id;
    private String name;
    private String description;
    private Integer avaliableQuantity;
    private Integer price;
    private Double averageVote;
    private String token;
    private String eventCategory;
    private String imageUrl;
}
