/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector;

import com.devstaff.farm.collector.entities.Harvest;
import com.devstaff.farm.collector.entities.Plantation;
import com.devstaff.farm.collector.resources.HarvestResource;
import com.devstaff.farm.collector.resources.PlantainManagementResource;
import lombok.extern.slf4j.Slf4j;
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
public class CollectorHarvestsCrudTests {

    @Autowired
    HarvestResource resource;

    @Test
    void getHarvestsBySeason() {
        Page<Harvest> harvestsBySeason = resource.getHarvestsBySeason(1, 10, FarmSeasons.Summer);
        Assert.isTrue(harvestsBySeason.isEmpty(), "items should be 0 in h2 database");
    }
}
