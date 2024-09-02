/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.resources;

import static com.devstaff.farm.collector.CollectorApplication.DATE_CREATED;
import com.devstaff.farm.collector.DataService;
import com.devstaff.farm.collector.FarmSeasons;
import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.entities.Harvest;
import com.devstaff.farm.collector.entities.Plantation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author daviestobialex
 */
@Tag(name = "Harvest Controller", description = "CRUD for harvest operations")
@Slf4j
@RestController
@RequestMapping("/harvest/")
@AllArgsConstructor
public class HarvestResource {

    @Getter
    private final DataService dataService;

    // create harvest against a farm
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "creates a harvest object against a farm object and quntity harvested must be in tons")
    @PostMapping(path = "farm")
    public void createHarvestByFarm(
            @RequestParam Long farmId,
            @RequestParam String cropType,
            @RequestParam FarmSeasons seasons,
            @RequestParam Double qunatityHarvested) {

        String msg = "farm id "
                .concat(farmId.toString())
                .concat(" not found");

        Farm farmRecord = dataService.getFarmRepository().findById(farmId)
                .orElseThrow(() -> new EntityNotFoundException(msg));

        Harvest harvestRecord = new Harvest();

        harvestRecord.setCropType(cropType);
        harvestRecord.setFarm(farmRecord);
        harvestRecord.setSeason(seasons);
        harvestRecord.setExpectedQuantity(qunatityHarvested);

        dataService.getHarvestRepository()
                .save(harvestRecord);

    }

    // create harvest against a plantation
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "creates a harvest object against a plantation object and qauntity harvested must be in tons")
    @PostMapping(path = "plantation")
    public void createHarvestByPlantation(
            @RequestParam Long plantationId,
            @RequestParam String cropType,
            @RequestParam FarmSeasons seasons,
            @RequestParam Double qunatityHarvested) {

        String msg = "plantation id "
                .concat(plantationId.toString())
                .concat(" not found");

        Plantation plantationRecord = dataService.getPlantationRepository().findById(plantationId)
                .orElseThrow(() -> new EntityNotFoundException(msg));

        Harvest harvestRecord = new Harvest();

        harvestRecord.setCropType(cropType);
        harvestRecord.setPlantation(plantationRecord);
        harvestRecord.setSeason(seasons);
        harvestRecord.setExpectedQuantity(qunatityHarvested);

        dataService.getHarvestRepository()
                .save(harvestRecord);

    }

    // get report per season
    @ResponseBody
    @GetMapping(path = "seasons/{farmSeason}")
    public Page<Harvest> getHarvestsBySeason(
            @RequestParam @NotNull(message = "page can not be null") Integer page,
            @RequestParam @NotNull(message = "size can not be null") Integer size,
            @PathVariable FarmSeasons farmSeason) {

        PageRequest of = PageRequest.of(--page, size, Sort.by(Sort.Direction.DESC, DATE_CREATED));

        CriteriaBuilder cb = dataService.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Harvest> query = cb.createQuery(Harvest.class);
        Root<Harvest> root = query.from(Harvest.class);

        List<Predicate> predicates = new ArrayList<>();

        if (farmSeason != null) {
            Path<String> namePath = root.get("season");
            predicates.add(cb.equal(namePath, farmSeason));
        }

        query.select(root)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.desc(root.get(DATE_CREATED)));

        List<Harvest> resultList = dataService.getEntityManager().createQuery(query)
                .getResultList();

        long count = dataService.getFarmRepository().count();

        return new PageImpl<>(resultList, of, count);

    }
}
