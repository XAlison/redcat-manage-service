package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.modules.sys.model.LoginModel;
import com.ywsoftware.oa.modules.sys.service.impl.AccountService;
import com.ywsoftware.oa.modules.sys.service.impl.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author yx
 */
@RestController
@RequestMapping("/api/manage")
public class ManagerController extends AbstractController {

    private final AccountService service;
    private final ManagerService managerService;

    @Autowired
    public ManagerController(AccountService service, ManagerService managerService) {
        this.service = service;
        this.managerService = managerService;
    }

    @GetMapping("/getAccountsByPhone")
    public ResponseEntity getAccountsByPhone(String phone) throws Exception {
        return ok(service.getAccountsByTenantId("getLoginCookie().getTenantId()", phone));
    }

    @PostMapping("/transferManager")
    public ResponseEntity transferManager(@RequestBody LoginModel model) throws Exception {
        managerService.transferManager(model, "getLoginCookie().getUserId()");
        return ok("ok");
    }

    @PostMapping("/checkCurrentManager")
    public ResponseEntity checkCurrentManager(@RequestBody LoginModel model) throws Exception {
        managerService.checkCurrentManager(model, /*getLoginCookie()*/null);
        return ok("ok");
    }

}
