/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.models;

import static com.devstaff.farm.collector.CollectorApplication.DATE_TIME;
import com.devstaff.farm.collector.FarmSeasons;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author daviestobialex
 */
@Data
@MappedSuperclass
public class PlantationDTO implements Serializable {

    /**
     * ignored at db level
     */
    @NotNull(message = "farm id can not be null, plantation must be attached to an existing farm")
    @Transient
    private Long farmId;

    private FarmSeasons season;

    @NotNull(message = "crop type can not be null or empty or blank")
    @Length(max = 150, message = "crop type can not be longer than 150 characters max")
    @Column(length = 150)
    private String cropType;
    
    @Schema(description = "planting area of crops in acres")
    private double plantingArea;
    @Schema(description = "expected quantity of harvested crops in tons")
    private double expectedQuantity;
    @Schema(example = "2020-02-24 10:30", description = "start date of the plantation")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date startPlantation;
    @Schema(example = "2020-02-24 10:30", description = "end date of the plantation, hence harvest date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME)
    private Date expectedHarvestDate;
}
