/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.entities;

import static com.devstaff.farm.collector.CollectorApplication.FULL_DATE_TIME;
import com.devstaff.farm.collector.models.FarmDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * This class object represents a Farm on the collectors system
 *
 * @author daviestobialex
 */
@Schema(name = "farm", description = "farm object")
@Data
@Entity
@Table(name = "collector_farm")
@SuppressWarnings("PersistenceUnitPresent")
@NoArgsConstructor
public class Farm extends FarmDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @OneToMany
    private Set<Plantation> plantations;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @Column(insertable = false, updatable = true)
    @UpdateTimestamp
    protected Timestamp lastModified;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_TIME)
    @CreationTimestamp
    protected Timestamp dateCreated;

    public Farm(String name) {
        super(name);
    }

}
