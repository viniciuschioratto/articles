package com.medium.articles.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Articles {
    private UUID id;
    private String title;
    private String description;
}
