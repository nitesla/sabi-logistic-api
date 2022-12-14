package com.sabi.logistics.api.controllers;


import com.sabi.framework.globaladminintegration.GlobalService;
import com.sabi.framework.globaladminintegration.request.BankRequest;
import com.sabi.framework.globaladminintegration.request.SingleRequest;
import com.sabi.framework.globaladminintegration.response.ListResponse;
import com.sabi.framework.globaladminintegration.response.PageResponse;
import com.sabi.framework.globaladminintegration.response.SingleResponse;
import com.sabi.framework.utils.Constants;
import com.sabi.logistics.service.services.LGAService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"lga")
public class LGAController {


    private final LGAService service;
    private final GlobalService globalService;

    public LGAController(LGAService service,GlobalService globalService) {
        this.service = service;
        this.globalService = globalService;
    }

    /** <summary>
     * LGA creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new lga</remarks>
     */

//    @PostMapping("")
//    public ResponseEntity<Response> createLga(@Validated @RequestBody LGADto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        LGAResponseDto response = service.createLga(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//    }



    /** <summary>
     * LGA update endpoint
     * </summary>
     * <remarks>this endpoint is responsible for updating lga</remarks>
     */

//    @PutMapping("")
//    public ResponseEntity<Response> updateLga(@Validated @RequestBody  LGADto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        LGAResponseDto response = service.updateLga(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Update Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


    /** <summary>
     * Get single record endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting a single record</remarks>
     */
//    @GetMapping("/{id}")
//    public ResponseEntity<Response> getLga(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        LGAResponseDto response = service.findLga(id);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


    @PostMapping("")
    public SingleResponse getLga (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSingleLga(request);
        return response;
    }


    @PostMapping("/page")
    public PageResponse getLgas (BankRequest request) throws Exception {
        PageResponse response= globalService.getLgaPagination(request);
        return response;
    }

    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        ListResponse response= globalService.getLgaList(request);
        return response;
    }

    /** <summary>
     * Get all records endpoint
     * </summary>
     * <remarks>this endpoint is responsible for getting all records and its searchable</remarks>
     */
//    @GetMapping("/page")
//    public ResponseEntity<Response> getLgas(@RequestParam(value = "name",required = false)String name,
//                                            @RequestParam(value = "stateId",required = false)Long stateId,
//                                              @RequestParam(value = "page") int page,
//                                              @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Page<LGA> response = service.findAll(name,stateId, PageRequest.of(page, pageSize));
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }



    /** <summary>
     * Enable disenable
     * </summary>
     * <remarks>this endpoint is responsible for enabling and disenabling a State</remarks>
     */

//    @PutMapping("/enabledisenable")
//    public ResponseEntity<Response> enableDisEnable(@Validated @RequestBody EnableDisEnableDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        service.enableDisEnableState(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }


//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "stateId",required = false)Long stateId){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<LGA> response = service.getAllByStateId(stateId);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }

//    @GetMapping("/active/list")
//    public ResponseEntity<Response> getAllByActive(@RequestParam(value = "isActive",required = false)Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<LGA> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }

}
