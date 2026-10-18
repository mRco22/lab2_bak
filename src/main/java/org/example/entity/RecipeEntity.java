package org.example.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeEntity
{
    private int id;
    private String nameFood;
    private List<RecipeItem> items = new ArrayList<>();

}