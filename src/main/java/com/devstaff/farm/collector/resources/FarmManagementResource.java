/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devstaff.farm.collector.resources;

import static com.devstaff.farm.collector.CollectorApplication.DATE_CREATED;
import com.devstaff.farm.collector.DataService;
import com.devstaff.farm.collector.entities.Farm;
import com.devstaff.farm.collector.models.FarmDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import static org.springframework.context.support.BeanDefinitionDslKt.beans;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * CRUD API Operations
 *
 * @author daviestobialex
 */
@Tag(name = "Farm Management Controller")
@Slf4j
@RestController
@RequestMapping("/management/")
@AllArgsConstructor
public class FarmManagementResource {

    @Getter
    private final DataService dataService;

    // create farm
    @Operation(description = "creates a farm")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "farm", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createFarm(@RequestBody FarmDTO farmObjectRequest) {

        Farm farmRecord = new Farm();
        BeanUtils.copyProperties(farmObjectRequest, farmRecord);

        dataService.getFarmRepository()
                .save(farmRecord);
    }

    // read all farms
    @ResponseBody
    @Operation(description = "paginated get farms")
    @GetMapping(path = "farms")
    public Page<Farm> getFarms(
            @RequestParam @NotNull(message = "page can not be null") Integer page,
            @RequestParam @NotNull(message = "size can not be null") Integer size,
            @RequestParam(required = false) String name) {

        PageRequest of = PageRequest.of(--page, size, Sort.by(Sort.Direction.DESC, DATE_CREATED));

        CriteriaBuilder cb = dataService.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> root = query.from(Farm.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isBlank()) {
            Path<String> namePath = root.get("name");
            predicates.add(cb.equal(namePath, name));
        }

        query.select(root)
                .where(predicates.toArray(Predicate[]::new))
                .orderBy(cb.desc(root.get(DATE_CREATED)));

        List<Farm> resultList = dataService.getEntityManager().createQuery(query)
                .getResultList();

        long count = dataService.getFarmRepository().count();

        return new PageImpl<>(resultList, of, count);

    }

    // read farm
    @ResponseBody
    @Operation(description = "get farm object by id")
    @GetMapping(path = "farm/{id}")
    public Farm getFarmById(@PathVariable Long id) {

        String msg = " farm with id ".concat(id.toString())
                .concat(" can not be found");
        return dataService.getFarmRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(msg));
    }

    // update farm
    @Operation(description = "creates a farm")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "farm/{farmId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateFarm(@PathVariable Long farmId, @RequestBody FarmDTO farmObjectRequest) {

        Farm farmRecord = getFarmById(farmId);
        BeanUtils.copyProperties(farmObjectRequest, farmRecord);

        dataService.getFarmRepository()
                .save(farmRecord);
    }

    // delete farm
    @Transactional
    @Operation(description = "deletes a farm, will throw errors if a plantation already exists with a farm")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "farm/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteFarm(@PathVariable Long id) {
        dataService.getFarmRepository()
                .deleteById(id);
    }

}
