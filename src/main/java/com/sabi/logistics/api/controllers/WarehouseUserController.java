package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.models.User;
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

    @GetMapping("/page")
    public ResponseEntity<Response> getWarehouseUser(@RequestParam(value = "firstName",required = false)String firstName,
                                                   @RequestParam(value = "phone",required = false)String phone,
                                                   @RequestParam(value = "email",required = false)String email,
                                                   @RequestParam(value = "username",required = false)String username,
                                                   @RequestParam(value = "roleId",required = false)Long roleId,
                                                   @RequestParam(value = "lastName",required = false)String lastName,
                                                  @RequestParam(value = "page") int page,
                                                  @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();

        Page<User> response = service.findByWareHouseId(firstName, phone, email, username, roleId, lastName, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }



    @GetMapping("/list")
    public ResponseEntity<Response> getAllUsers(@RequestParam(value = "isActive")Boolean isActive){
        HttpStatus httpCode ;
        Response resp = new Response();

        List<User> response = service.getAllUsers(isActive);
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

        List<WarehouseUser> response = service.findWareHouseUsers(wareHouseId,isActive);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);

    }


}
