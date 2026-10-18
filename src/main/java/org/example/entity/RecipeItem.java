package org.example.entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeItem //статичный класс
{
    private int ingredientId;
    private double quantity; //количество на ед продукции
}