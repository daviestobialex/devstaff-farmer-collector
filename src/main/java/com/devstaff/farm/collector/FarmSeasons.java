/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.devstaff.farm.collector;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * mocks the four farming seasons
 * @author daviestobialex
 */
@Schema(description = "mocks the four farming seasons")
public enum FarmSeasons {
    Autumn,
    Spring,
    Winter,
    Summer;
}
