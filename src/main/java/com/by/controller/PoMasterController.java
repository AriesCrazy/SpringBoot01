package com.by.controller;

import com.by.entity.PoMaster;
import com.by.entity.PoMasterQuery;
import com.by.service.PoMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ;(po_master)控制层
 *
 * @author : heimi
 * @date : 2025-6-12
 */
@RestController
@RequestMapping("/api/po")
public class PoMasterController {
    @Autowired
    private PoMasterService poMasterService;

    /**
     * 查询接口
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public List<PoMaster> select(PoMasterQuery query) {
        return poMasterService.select(query);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer insert(@RequestBody PoMaster product) {
        return poMasterService.insert(product);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer update(@RequestBody PoMaster product) {
        return poMasterService.update(product);
    }

    @GetMapping("/approve")
    @PreAuthorize("hasPermission('PO_APPROVE.BUTTON','PO_APPROVE')")
    public Integer approve(@RequestParam String id) {
        return poMasterService.approve(id);
    }

    @GetMapping("/reject")
    @PreAuthorize("hasPermission('PO_REJECT.BUTTON', 'PO_REJECT')")
    public Integer reject(@RequestParam String id) {
        return poMasterService.reject(id);
    }

    @GetMapping("/interrupt")
    @PreAuthorize("hasPermission('PO_INTERRUPT.BUTTON','PO_INTERRUPT')")
    public Integer interrupt(@RequestParam String id) {
        return poMasterService.interrupt(id);
    }

    @GetMapping("/startdelivery")
    @PreAuthorize("hasPermission('PO_STARTDELIVERY.BUTTON','PO_STARTDELIVERY')")
    public Integer startDelivery(@RequestParam String id) {
        return poMasterService.startDelivery(id);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('ROLE_PURCHASE_MANAGER', 'ROLE_PURCHASE','ROLE_ADMIN')")
    public Integer delete(Integer id) {
        return poMasterService.delete(id);
    }
}