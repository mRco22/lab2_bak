package org.example.entity;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Ingredients_Entity
{
    private int IngredientId;
    private String IngredientName;
    private String IngredientUnit;
    private Double IngredientStock;
}