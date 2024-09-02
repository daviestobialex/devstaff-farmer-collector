/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector;

import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.entities.Plantation;
import com.devstaff.farm.collector.resources.PlantainManagementResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author daviestobialex
 */
@Slf4j
@SpringBootTest
public class CollectorPlantationCrudTests {

    @Autowired
    PlantainManagementResource resource;

    @BeforeEach
    public void createFarmForTest() {
        
        Farm farm;
        long count = resource.getDataService().getFarmRepository().count();
        if (count == 0) {
            farm = resource.getDataService().getFarmRepository()
                    .save(new Farm("Davies-Farm"));
        }
        
        count = resource.getDataService().getPlantationRepository().count();
        
        if (count == 0) {
            Plantation plantation = new Plantation();
            resource.getDataService().getPlantationRepository()
                    .save(plantation);
        }
    }

}
