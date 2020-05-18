package com.cdn.springsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CDN
 * 2020/05/18 02:54
 */
@RestController
@RequestMapping("example")
public class ExampleController {

    //---------------------------------- 权限  示范 ------------------------------------------
    @GetMapping("/write")
    @PreAuthorize("hasAuthority('write')")
    public String getWrite() {
        return "have a write authority";
    }

    @GetMapping("/read")
    @PreAuthorize("hasAuthority('user:update')")
    public String readDate() {
        return "have a user:update authority";
    }

    @GetMapping("/read-or-write")
    @PreAuthorize("hasAnyAuthority('read','write')")
    public String readWriteDate() {
        return "have a read or write authority";
    }

    @GetMapping("/admin-role")
    @PreAuthorize("hasRole('admin')")
    public String readAdmin() {
        return "have a admin role";
    }

    @GetMapping("/user-role")
    @PreAuthorize("hasRole('me')")
    public String readUser() {
        return "have a user role";
    }
}
