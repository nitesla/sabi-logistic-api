package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import com.sabi.logistics.core.dto.request.WareHouseUserRequestDto;
import com.sabi.logistics.core.dto.response.WareHouseUserResponseDto;
import com.sabi.logistics.core.models.WarehouseUser;
import com.sabi.logistics.service.services.WarehouseUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SuppressWarnings("ALL")
@RestController
@RequestMapping(Constants.APP_CONTENT+"warehouseuser")
public class WarehouseUserController {

    private final WarehouseUserService service;



    public WarehouseUserController(WarehouseUserService service) {
        this.service = service;

    }

    /** <summary>
     * WareHouse User creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new WareHouse User</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createWareHouseUser(@Validated @RequestBody List<WareHouseUserRequestDto> request){
        HttpStatus httpCode ;
        Response resp = new Response();
        List<WareHouseUserResponseDto> response = service.createWareHouseUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }

    /** <summary>
     * WarehouseUser update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating WarehouseUser</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updateWareHouseUser(@Validated @RequestBody WareHouseUserRequestDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        WareHouseUserResponseDto response = service.updateWareHouseUser(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Update Successful");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Response> deleteWarehouseUserById(@PathVariable long id){

        HttpStatus httpCode ;
        Response resp = new Response();
        WareHouseUserResponseDto response = service.deleteWareHouseUser(id);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record Deleted !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getAllWarehouseUsers(
                                                   @RequestParam(value = "wareHouseId",required = false)Long wareHouseId,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();

        Page<WarehouseUser> response = service.findAll(wareHouseId, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }

    @GetMapping("")
    public ResponseEntity<Response> findWareHouseUsers(@RequestParam(value = "wareHouseId")Long wareHouseId,
                                                       @RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();

        List<WarehouseUser> response = service.getAll(wareHouseId,isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }


}
