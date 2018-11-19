package com.ywsoftware.oa.modules.sys.service.impl;

import com.ywsoftware.oa.modules.sys.domain.entity.UserTenant;
import com.ywsoftware.oa.modules.sys.service.mapper.TenantMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MultitenantService {
    private TenantMapper tenantMapper;
    private ResourceLoader resourceLoader;

    private final String logoPath = "/uploadFiles/logos/";

    public MultitenantService(TenantMapper tenantMapper, ResourceLoader resourceLoader) {
        this.tenantMapper = tenantMapper;
        this.resourceLoader = resourceLoader;
    }

    public UserTenant getTenantInfoById(String tenantId) throws Exception {
        return tenantMapper.getTenantInfoById(tenantId);
    }

    // 更新租户
    public void updateTenant(Map<String, String[]> datas, MultipartFile multipartFile) throws Exception {
        UserTenant tenant = new UserTenant();
        tenant.setId(datas.get("id")[datas.get("id").length - 1]);
        tenant.setTenantName(datas.get("tenantName")[datas.get("tenantName").length - 1]);
        if (multipartFile != null) {
            tenant.setLogo(getTenantLogo(multipartFile, tenant));
            tenantMapper.updateTenantNameAndLogo(tenant);
        } else {
            tenantMapper.updateTenantName(tenant);
        }

    }

    private String getTenantLogo(MultipartFile multipartFile, UserTenant tenant) throws IOException {
        String realPath = getClasspath() + logoPath;
        String url = null;
        File file = new File(realPath);
        if (!multipartFile.isEmpty()) {
            if (!file.exists()) {
                file.mkdirs();
            }
            String name = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String suffix = (name.lastIndexOf('.') >= 0) ? name.substring(name.lastIndexOf('.')) : "";
            file = new File(realPath, uuid + suffix);
            multipartFile.transferTo(file);
            url = logoPath + uuid + suffix;
        }
        return url;
    }

    private static String getClasspath() throws FileNotFoundException {
        return ResourceUtils.getFile("file:").getAbsolutePath();
    }

    public void getFile(String fileName, HttpServletResponse response) throws Exception {
        File file = new File(getClasspath() + logoPath + fileName);
        InputStream input = new FileInputStream(file);
        byte[] byt = new byte[input.available()];
        input.read(byt);
        response.getOutputStream().write(byt);
    }
}
