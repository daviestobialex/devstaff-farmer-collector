/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.models;

import static com.devstaff.farm.collector.CollectorApplication.PATTERN_STRING_6;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * Farm data type object
 *
 * @author daviestobialex
 */
@MappedSuperclass
@Data
@NoArgsConstructor
public class FarmDTO implements Serializable {

    @NotBlank(message = "farm name can not be null, empty or blank")
    @Pattern(regexp = PATTERN_STRING_6, message = "first name must contain letter alone")
    @Length(max = 150, message = "farm name can not be longer than 150 characters max")
    @Column(length = 150, unique = true)
    private String name;

    @Lob
    private String description;

    private BigDecimal latitude;
    private BigDecimal longitude;

    public FarmDTO(String name) {
        this.name = name;
    }
}
