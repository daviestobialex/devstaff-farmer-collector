/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector;

import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.entities.Plantation;
import com.devstaff.farm.collector.resources.PlantainManagementResource;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

/**
 *
 * @author daviestobialex
 */
@Slf4j
@SpringBootTest
public class CollectorPlantationCrudTests {

    @Autowired
    PlantainManagementResource resource;

    @Test
    void getPlantations() {

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            Plantation plantations = resource.getPlantationById(1L);
            Assert.isNull(plantations, "items should be 0 in h2 database");
        });
        String expectedMessage = "plantation with id 1 can not be found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
