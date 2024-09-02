/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector;

import com.devstaff.farm.collector.entities.repositories.FarmRepository;
import com.devstaff.farm.collector.entities.repositories.HarvestRepository;
import com.devstaff.farm.collector.entities.repositories.PlantationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * collects all entity repositories
 *
 * @author daviestobialex
 */
@Getter
@Service
public final class DataService {

    private final FarmRepository farmRepository;
    private final PlantationRepository plantationRepository;
    private final HarvestRepository harvestRepository;
    @PersistenceContext
    protected final EntityManager entityManager;

    @Autowired
    public DataService(FarmRepository farmRepository,
            PlantationRepository plantationRepository,
            HarvestRepository harvestRepository,
            EntityManager entityManager) {
        this.farmRepository = farmRepository;
        this.plantationRepository = plantationRepository;
        this.harvestRepository = harvestRepository;
        this.entityManager = entityManager;
    }

}
