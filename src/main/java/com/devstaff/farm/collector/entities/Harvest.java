/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.entities;

import static com.devstaff.farm.collector.CollectorApplication.FULL_DATE_TIME;
import com.devstaff.farm.collector.FarmSeasons;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author daviestobialex
 */
@Schema(name = "farm", description = "farm object")
@Data
@Entity
@Table(name = "collector_harvest")
@SuppressWarnings("PersistenceUnitPresent")
public class Harvest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(optional = true)
    private Farm farm;

    @ManyToOne(optional = true)
    private Plantation plantation;

    private FarmSeasons season;

    @Column(length = 150)
    private String cropType;
    
    @Schema(description = "expected quantity of harvested crops in tons")
    private double expectedQuantity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @Column(insertable = false, updatable = true)
    @UpdateTimestamp
    protected Timestamp lastModified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @CreationTimestamp
    protected Timestamp dateCreated;
}
