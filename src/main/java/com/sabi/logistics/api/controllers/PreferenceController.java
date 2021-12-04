package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PreferenceDto;
import com.sabi.logistics.core.dto.response.PreferenceResponseDto;
import com.sabi.logistics.core.models.Preference;
import com.sabi.logistics.service.services.PreferenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"preference")
public class PreferenceController {

    private final PreferenceService service;

    public PreferenceController(PreferenceService service) {
        this.service = service;
    }

    /** <summary>
     * preference creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new preference</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createPreference(@Validated @RequestBody PreferenceDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PreferenceResponseDto response = service.createPreference(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Preference update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating preference</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updatePreference(@Validated @RequestBody  PreferenceDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PreferenceResponseDto response = service.updatePreference(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Response> getPreference(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        Preference response = service.findPreferenceByPartnerId(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
