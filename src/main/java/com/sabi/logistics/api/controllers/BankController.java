package com.sabi.logistics.api.controllers;

import com.sabi.framework.globaladminintegration.GlobalService;
import com.sabi.framework.globaladminintegration.request.BankRequest;
import com.sabi.framework.globaladminintegration.request.SingleRequest;
import com.sabi.framework.globaladminintegration.response.ListResponse;
import com.sabi.framework.globaladminintegration.response.PageResponse;
import com.sabi.framework.globaladminintegration.response.SingleResponse;
import com.sabi.framework.service.WhatsAppService;
import com.sabi.framework.utils.Constants;
import com.sabi.logistics.service.services.BankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT +"bank")
public class BankController {


    private final BankService service;
    private final WhatsAppService whatsAppService;
    private final GlobalService globalService;

    public BankController(BankService service,WhatsAppService whatsAppService,GlobalService globalService) {
        this.service = service;
        this.whatsAppService = whatsAppService;
        this.globalService = globalService;
    }


    /** <summary>
     * Bank creation endpoint
     * </summary>
     * <remarks>this endpoint is responsible for creation of new bank</remarks>
     */
//    @PostMapping("")
//    public ResponseEntity<Response> createBank(@Validated @RequestBody BankDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.createBank(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        resp.setData(response);
//        httpCode = HttpStatus.CREATED;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
////    @PostMapping("/whatsapp")
////    public WhatsAppResponse whatsApp (@Validated @RequestBody WhatsAppRequest whatsAppRequest){
////
////        WhatsAppResponse response = whatsAppService.whatsAppNotification(whatsAppRequest);
////
////        return response;
////
////    }
//
//
//    /** <summary>
//     * Bank update endpoint
//     * </summary>
//     * <remarks>this endpoint is responsible for updating bank</remarks>
//     */
//
//    @PutMapping("")
//    public ResponseEntity<Response> updateBank(@Validated @RequestBody  BankDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.updateBank(request);
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
//    public ResponseEntity<Response> getBank(@PathVariable Long id){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        BankResponseDto response = service.findBank(id);
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
//    public ResponseEntity<Response> getBanks(@RequestParam(value = "name",required = false)String name,
//                                              @RequestParam(value = "code",required = false)String code,
//                                              @RequestParam(value = "page") int page,
//                                              @RequestParam(value = "pageSize") int pageSize){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        Page<Bank> response = service.findAll(name,code, PageRequest.of(page, pageSize));
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    /** <summary>
//     * Enable disenable
//     * </summary>
//     * <remarks>this endpoint is responsible for enabling and disenabling a bank</remarks>
//     */
//
//    @PutMapping("/enabledisable")
//    public ResponseEntity<Response> enableDisable(@Validated @RequestBody EnableDisEnableDto request){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        service.enableDisable(request);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Successful");
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }
//
//
//
//    @GetMapping("/list")
//    public ResponseEntity<Response> getAll(@RequestParam(value = "isActive")Boolean isActive){
//        HttpStatus httpCode ;
//        Response resp = new Response();
//        List<Bank> response = service.getAll(isActive);
//        resp.setCode(CustomResponseCode.SUCCESS);
//        resp.setDescription("Record fetched successfully !");
//        resp.setData(response);
//        httpCode = HttpStatus.OK;
//        return new ResponseEntity<>(resp, httpCode);
//    }




    @PostMapping("")
    public SingleResponse getBank (SingleRequest request) throws Exception {
        SingleResponse response= globalService.getSingleBank(request);
        return response;
    }


    @PostMapping("/page")
    public PageResponse getBanks (BankRequest request) throws Exception {
        PageResponse response= globalService.getBankPagination(request);
        return response;
    }

    @PostMapping("/list")
    public ListResponse getAll (BankRequest request) throws Exception {
        ListResponse response= globalService.getBankList(request);
        return response;
    }
}
