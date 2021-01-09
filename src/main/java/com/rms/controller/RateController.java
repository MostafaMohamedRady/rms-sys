package com.rms.controller;

import com.rms.constant.AppConstant;
import com.rms.dto.RateRequest;
import com.rms.dto.RateResponse;
import com.rms.service.RateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Api("Rate Controller")
@RestController
@RequestMapping("/v1/rms/rate")
public class RateController {

    private static final Logger log = LoggerFactory.getLogger(RateController.class);

    @Autowired
    private RateService rateService;

    @ApiOperation(value = "Search Rate.", response = RateResponse.class)
    @ApiResponses({@ApiResponse(code = 200, message = "All the fields of the Rate table and surcharge details."),
            @ApiResponse(code = 404, message = "RateId not found in RMS."),
            @ApiResponse(code = 500, message = "Internal server error. Please contact admin.")})
    @GetMapping("/search/{rateId}")
    public ResponseEntity<Object> searchRate(@NotNull @PathVariable Long rateId) {
        log.info("Search Rate started");
        RateResponse rateResponse = rateService.searchRate(rateId);
        log.info("Search response :- {}", rateResponse);
        return ResponseEntity.ok(rateResponse);
    }

    @ApiOperation(value = "Add Rate.", response = String.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully store rate details in DB."),
            @ApiResponse(code = 400, message = "Request is not valid."),
            @ApiResponse(code = 500, message = "Internal server error. Please contact admin.")})
    @PostMapping("/add")
    public ResponseEntity<Object> addRate(@Valid @RequestBody RateRequest rateRequest) {
        log.info("Add Rate started");
        rateService.addRate(rateRequest);
        return ResponseEntity.ok(AppConstant.ADD_RATE_SUCCESS);
    }

    @ApiOperation(value = "Update Rate.", response = String.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully update rate details in DB."),
            @ApiResponse(code = 400, message = "Request is not valid"),
            @ApiResponse(code = 500, message = "Internal server error. Please contact admin.")})
    @PutMapping("/update")
    public ResponseEntity<Object> updateRate(@Valid @RequestBody RateRequest rateRequest) {
        log.info("Update Rate started");
        rateService.updateRate(rateRequest);
        return ResponseEntity.ok(AppConstant.UPDATE_RATE_SUCCESS);
    }

    @ApiOperation(value = "Delete Rate.", response = String.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Successfully delete Rate record based on RateId"),
            @ApiResponse(code = 500, message = "Internal server error. Please contact admin.")})
    @DeleteMapping("/delete/{rateId}")
    public ResponseEntity<Object> deleteRate(@NotNull @PathVariable Long rateId) {
        log.info("Delete rate started");
        rateService.deleteRate(rateId);
        return ResponseEntity.ok(AppConstant.DELETE_RATE_SUCCESS);
    }
}
