package com.ywsoftware.oa.modules.sys.controller;

import com.ywsoftware.oa.modules.sys.domain.entity.UserTenant;
import com.ywsoftware.oa.modules.sys.service.impl.MultitenantService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/tenant")
public class TenantController extends AbstractController {
    private MultitenantService multitenantService;

    public TenantController(MultitenantService multitenantService) {
        this.multitenantService = multitenantService;
    }

    @GetMapping("/uploadFiles/logos/{filename}")
    public void getFile(@PathVariable String filename, HttpServletResponse response) throws Exception {
        multitenantService.getFile(filename, response);
    }

    /**
     * 更新租户
     */
    @PostMapping("updateTenant")
    public void updateTenant(HttpServletRequest request, MultipartFile file) throws Exception {
        multitenantService.updateTenant(request.getParameterMap(), file);
    }

    @GetMapping("getTenantInfoByTenantId")
    public UserTenant getTenantInfo() throws Exception {
        return multitenantService.getTenantInfoById(/*getLoginCookie().getTenantId()*/null);
    }
}
