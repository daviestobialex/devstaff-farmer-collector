package com.devstaff.farm.collector;

import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.models.FarmDTO;
import com.devstaff.farm.collector.resources.FarmManagementResource;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest
public class CollectorsFarmCrudTests {

    @Autowired
    private FarmManagementResource resource;

    @BeforeEach
    public void createFarmForTest() {

        long count = resource.getDataService().getFarmRepository().count();
        if (count == 0) {
            resource.getDataService().getFarmRepository()
                    .save(new Farm("Davies-Farm"));
        }
    }

    @Test
    void createFarm() {
        resource.createFarm(new FarmDTO("Alex-Farm"));
        long count = resource.getDataService().getFarmRepository().count();
        log.debug("AFTER SAVE COUNT :: {}", count);
        Assert.isTrue(count == 2, "items should be 2 in h2 database");

    }

    // runs last after delete, ideally expected should be 2
    @Test
    void getFarms() {
        Page<Farm> farms = resource.getFarms(1, 10, null);
        Assert.isTrue(farms.getTotalElements() == 1L, "items should be 1 in h2 database");
    }

    @Test
    void getFarmsByName() {
        Page<Farm> farms = resource.getFarms(1, 10, "Davies-Farm");
        Assert.isTrue(farms.getTotalElements() == 1L, "items should be 1 in h2 database");
    }

    @Test
    void getFarmById() {
        Farm farmById = resource.getFarmById(1L);
        Assert.notNull(farmById, "farm by id object can not be null");
    }

    @Test
    void updateFarm() {
        resource.updateFarm(1L, new FarmDTO("Tobi-Farm"));
        Optional<Farm> findById = resource.getDataService()
                .getFarmRepository().findById(1L);
        Assert.hasText(findById.get().getName(), "expected Tobi-Farm");
    }

    @Test
    void deleteFarm() {
        resource.deleteFarm(1L);
        long count = resource.getDataService().getFarmRepository().count();
        Assert.isTrue(count == 1, "items should be 1 in h2 database");
    }
}
