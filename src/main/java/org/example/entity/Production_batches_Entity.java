package org.example.entity;

import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter
@Setter
public class Production_batches_Entity
{
    private int ProductionBatchesId;
    private int ProductionBatchesRecipesId;
    private int ProductionBatchesQuantity;
    private String ProductionBatchesStatus = "Planned";
}