package com.sabi.logistics.api.controllers;

import com.sabi.framework.dto.requestDto.EnableDisEnableDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.PartnerBankDto;
import com.sabi.logistics.core.dto.response.PartnerBankResponseDto;
import com.sabi.logistics.core.models.PartnerBank;
import com.sabi.logistics.service.services.PartnerBankService;
import lombok.var;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"partnerbank")
public class PartnerBankController {


    private final PartnerBankService service;

    public PartnerBankController(PartnerBankService service) {
        this.service = service;
    }


    /** <summary>
     * PartnerBank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new partnerBank</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createPartnerBank(@Validated @RequestBody PartnerBankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerBankResponseDto response = service.createPartnerBank(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * PartnerBank update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating partnerBank</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updatePartnerBank(@Validated @RequestBody  PartnerBankDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerBankResponseDto response = service.updatePartnerBank(request);
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
    public ResponseEntity<Response> getPartnerBank(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PartnerBankResponseDto response = service.findPartnerBank(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
    @GetMapping("")
    public ResponseEntity<Response> getPartnerBanks(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                                    @RequestParam(value = "bankId",required = false)Long bankId,
                                                    @RequestParam(value = "accountNumber",required = false)String accountNumber,
                                                    @RequestParam(value = "page") int page,
                                                    @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<PartnerBank> response = service.findAll(partnerId, bankId, accountNumber, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a partnerBank</remarks>
     */

    @PutMapping("/enabledisable")
    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        service.enableDisable(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAll(@RequestParam(value = "partnerId",required = false)Long partnerId,
                                           @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<PartnerBank> response = service.getAll(partnerId, isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/isDefault/{id}")
    public ResponseEntity<Response> setDefault(@PathVariable("id") long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        var res = service.setDefalult(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(res);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}
