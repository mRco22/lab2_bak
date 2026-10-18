package org.example.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductionEntity
{
    private int id;
    private int recipeId; //ссылка на рецепт
    private int quantity; //кол-во изделий
    private String status = "Planned"; //4 статуса: в заявке, готовится, отменен, готов
}