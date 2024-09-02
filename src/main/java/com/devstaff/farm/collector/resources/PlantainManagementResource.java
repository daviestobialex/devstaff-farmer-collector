/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.resources;

import com.devstaff.farm.collector.DataService;
import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.entities.Plantation;
import com.devstaff.farm.collector.models.PlantationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD for Plantation Object functionality
 *
 * @author daviestobialex
 */
@Tag(name = "Plantation Management Controller")
@Slf4j
@RestController
@RequestMapping("/management/")
@AllArgsConstructor
public class PlantainManagementResource {

    @Getter
    private final DataService dataService;

    // create plantation
    @Operation(description = "creates a plantation")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "plantation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createPlantation(@RequestBody PlantationDTO plantationObjectRequest) {
        String msg = "farm id "
                .concat(plantationObjectRequest.getFarmId().toString())
                .concat(" not found");

        Farm farmRecord = dataService.getFarmRepository().findById(plantationObjectRequest.getFarmId())
                .orElseThrow(() -> new EntityNotFoundException(msg));

        Plantation plantationRecord = new Plantation();
        BeanUtils.copyProperties(plantationObjectRequest, plantationRecord);
        plantationRecord.setFarm(farmRecord);

        dataService.getPlantationRepository()
                .save(plantationRecord);
    }

    // read plantation
    @ResponseBody
    @Operation(description = "get plantation object by id")
    @GetMapping(path = "plantation/{id}")
    public Plantation getPlantationById(@PathVariable Long id) {

        String msg = " plantation with id ".concat(id.toString())
                .concat(" can not be found");
        return dataService.getPlantationRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(msg));
    }

    // update plantation
    @Operation(description = "updates a farm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "plantation/{farmId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePlantation(@PathVariable Long plantationId, @RequestBody PlantationDTO plantationObjectRequest) {

        Plantation plantationRecord = getPlantationById(plantationId);
        BeanUtils.copyProperties(plantationObjectRequest, plantationRecord);

        dataService.getPlantationRepository()
                .save(plantationRecord);
    }

    // delete plantation
    @Transactional
    @Operation(description = "deletes a plantation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "plantation/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePlantation(@PathVariable Long id) {
        dataService.getPlantationRepository()
                .deleteById(id);
    }
}
