/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.entities;

import static com.devstaff.farm.collector.CollectorApplication.FULL_DATE_TIME;
import com.devstaff.farm.collector.models.PlantationDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * plantation class object
 *
 * @author daviestobialex
 */
@Schema(name = "plantation", description = "plantation object represents the collectors intent to have planted a crop")
@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "collector_plantation")
public class Plantation extends PlantationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * ignored due to back reference exception
     */
    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Farm farm;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @Column(insertable = false, updatable = true)
    @UpdateTimestamp
    protected Timestamp lastModified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @CreationTimestamp
    protected Timestamp dateCreated;
}
