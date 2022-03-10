package com.sabi.logistics.api.controllers;


import com.sabi.framework.globaladminintegration.GlobalService;
import com.sabi.framework.globaladminintegration.request.BankRequest;
import com.sabi.framework.globaladminintegration.request.SingleRequest;
import com.sabi.framework.globaladminintegration.response.ListResponse;
import com.sabi.framework.globaladminintegration.response.PageResponse;
import com.sabi.framework.globaladminintegration.response.SingleResponse;
import com.sabi.framework.utils.Constants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.APP_CONTENT+"permission")
public class PermissionController {




    private final GlobalService globalService;

    public PermissionController(GlobalService globalService) {
        this.globalService = globalService;
    }


    /** <summary>
     * Permission creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new permission</remarks>
     */

//    @PostMapping("")
//    public ResponseEntity<Response> createPermission(@Validated @RequestBody PermissionDto request,HttpServletRequest request1){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        PermissionResponseDto response = service.createPermission(request,request1);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Permission update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating permission</remarks>
//     */
//
//    @PutMapping("")
//    public ResponseEntity<Response> updatePermission(@Validated @RequestBody  PermissionDto request,HttpServletRequest request1){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        PermissionResponseDto response = service.updatePermission(request,request1);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Get single record endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting a single record</remarks>
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Response> getPermission(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        PermissionResponseDto response = service.findPermission(id);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//    /** <summary>
//     * Get all records endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
//     */
//    @GetMapping("")
//    public ResponseEntity<Response> getPermissions(@RequestParam(value = "name",required = false)String name,
//                                                   @RequestParam(value = "isActive",required = false)Boolean isActive,
//                                             @RequestParam(value = "page") int page,
//                                             @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Page<Permission> response = service.findAll(name,isActive, PageRequest.of(page, pageSize));
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<Permission> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }





    @PostMapping("")
    public SingleResponse getPermission (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSinglePermission(request);
        return response;
    }






    @PostMapping("/page")
    public PageResponse getPermissions (BankRequest request) throws Exception {
        request.setAppPermission(Constants.LOGISTICS);
        PageResponse response= globalService.getPermissionPagination(request);
        return response;
    }



    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a State</remarks>
     */


    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        request.setAppPermission(Constants.LOGISTICS);
        ListResponse response= globalService.getPermissionList(request);
        return response;
    }
}
