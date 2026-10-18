package org.example.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientEntity
{
    private int id;
    private String nameIngredients;
    private String unit;
    private double countNow;
}