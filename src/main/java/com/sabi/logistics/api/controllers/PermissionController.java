package com.sabi.logistics.api.controllers;


import com.sabi.framework.dto.requestDto.PermissionDto;
import com.sabi.framework.dto.responseDto.PermissionResponseDto;
import com.sabi.framework.dto.responseDto.Response;
import com.sabi.framework.models.Permission;
import com.sabi.framework.service.PermissionService;
import com.sabi.framework.utils.Constants;
import com.sabi.framework.utils.CustomResponseCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.APP_CONTENT+"permission")
public class PermissionController {



    private final PermissionService service;

    public PermissionController(PermissionService service) {
        this.service = service;
    }


    /** <summary>
     * Permission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new permission</remarks>
     */

    @PostMapping("")
    public ResponseEntity<Response> createPermission(@Validated @RequestBody PermissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PermissionResponseDto response = service.createPermission(request);
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Successful");
        resp.setData(response);
        httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    /** <summary>
     * Permission update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating permission</remarks>
     */

    @PutMapping("")
    public ResponseEntity<Response> updatePermission(@Validated @RequestBody  PermissionDto request){
        HttpStatus httpCode ;
        Response resp = new Response();
        PermissionResponseDto response = service.updatePermission(request);
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
    public ResponseEntity<Response> getPermission(@PathVariable Long id){
        HttpStatus httpCode ;
        Response resp = new Response();
        PermissionResponseDto response = service.findPermission(id);
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
    public ResponseEntity<Response> getPermissions(@RequestParam(value = "name",required = false)String name,
                                                   @RequestParam(value = "isActive",required = false)Boolean isActive,
                                             @RequestParam(value = "page") int page,
                                             @RequestParam(value = "pageSize") int pageSize){
        HttpStatus httpCode ;
        Response resp = new Response();
        Page<Permission> response = service.findAll(name,isActive, PageRequest.of(page, pageSize));
        resp.setCode(CustomResponseCode.SUCCESS);
        resp.setDescription("Record fetched successfully !");
        resp.setData(response);
        httpCode = HttpStatus.OK;
        return new ResponseEntity<>(resp, httpCode);
    }
}