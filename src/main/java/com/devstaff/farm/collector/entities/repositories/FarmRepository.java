/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devstaff.farm.collector.entities.repositories;

import com.devstaff.farm.collector.entities.Farm;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author daviestobialex
 */
public interface FarmRepository extends CrudRepository<Farm, Long>{
    
}
