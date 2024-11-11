package com.engineering_bureau.corporate_service.dto.request.project;

import lombok.Data;

@Data
public class ProjectSearchRq {

    private Long id;

    private String title;

    private String client;

    private String responsible;

    private String description;
}
