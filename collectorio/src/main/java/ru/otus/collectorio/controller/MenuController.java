package ru.otus.collectorio.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.collectorio.payload.response.ApiResponse;

@RestController
public class MenuController {

    @GetMapping("/api/admin/enterprise/menu/self/info")
    public ResponseEntity<ApiResponse> selfInfo() {

        return ResponseEntity.ok(ApiResponse.success());
        //ApiResponse.success(enterpriseMenuService.queryPermissionAndMenuByAdministrator(getAdministrator()));
    }

    @GetMapping("/api/admin/enterprise/menu/list")
    public ResponseEntity<ApiResponse> menuList() {

        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/api/admin/enterprise/menu/select/list")
    public ResponseEntity<ApiResponse> menuSelectList() {

        return ResponseEntity.ok(ApiResponse.success());
    }

}
