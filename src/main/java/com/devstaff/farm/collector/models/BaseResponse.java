/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devstaff.farm.collector.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author daviestobialex
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class BaseResponse implements Serializable {

    protected String responseMessage;

    @Schema(description = "this object is only avialable for issues as a result of invalid parameter passed to the service")
    protected List<String> errors;

    public BaseResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
