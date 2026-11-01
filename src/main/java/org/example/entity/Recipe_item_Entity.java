package org.example.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipe_item_Entity
{
    private int ingredientId;// id ингредиента из Ingredients
    private Double quantity;// сколько его нужно на одно изделие
    private String name;
}