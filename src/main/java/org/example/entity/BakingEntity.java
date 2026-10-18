package org.example.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BakingEntity
{
    private int id;
    private int userId;
    private int recipeId;
    private int quantity;                  // количество изделий
    private Integer batchId; // назначенная партия (null, пока не назначена)
    private String status = "New";         // New, Assigned, Done, Canceled

}