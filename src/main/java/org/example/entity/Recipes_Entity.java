package org.example.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Recipes_Entity
{
    private int RecipesId;
    private String RecipesName;
    private List<Recipe_item_Entity>RecipesList = new ArrayList<>();
}