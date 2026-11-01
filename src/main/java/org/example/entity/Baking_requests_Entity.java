package org.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Baking_requests_Entity
{
    private int BakingRequestsId;
    private Integer BakingRequestsUserId;
    private Integer BakingRequestsRecipesId;
    private Integer BakingRequestsBatchId;
    private Integer BakingRequestsQuantity;
    private String BakingRequestsStatus = "New";
}