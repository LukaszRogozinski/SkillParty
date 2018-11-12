package com.engineer.lrogozinski.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class EventCategoryDto {
    private String name;
    private String imageUrl;
}
