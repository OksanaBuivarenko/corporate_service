package com.engineering_bureau.corporate_service.controller.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.PageSizeRq;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentRq;
import com.engineering_bureau.corporate_service.dto.request.Marker;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentSearchRq;
import com.engineering_bureau.corporate_service.dto.response.storage.EquipmentRs;
import com.engineering_bureau.corporate_service.service.EquipmentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Operation(description = "Getting all equipment")
    @GetMapping()
    public PageRs<List<EquipmentRs>> getAllEquipmentRs(@ParameterObject @Valid PageSizeRq req) {
        return equipmentService.getAllEquipmentRs(req.getOffset(), req.getLimit());
    }

    @Operation(description = "Getting equipment by id")
    @GetMapping("/{id}")
    public EquipmentRs getEquipmentRsById(@PathVariable Long id) {
        return equipmentService.getEquipmentRsById(id);
    }

    @Validated({Marker.OnCreate.class})
    @Operation(description = "Create equipment")
    @PostMapping()
    public EquipmentRs createEquipment(@RequestBody @Valid EquipmentRq equipmentRq) {
        return equipmentService.createEquipment(equipmentRq);
    }

    @Validated({Marker.OnUpdate.class})
    @Operation(description = "Update equipment by id")
    @PutMapping("/{id}")
    public EquipmentRs updateEquipmentById(@PathVariable Long id, @RequestBody @Valid EquipmentRq equipmentRq) {
        return equipmentService.updateEquipmentById(id, equipmentRq);
    }

    @Operation(description = "Delete equipment")
    @DeleteMapping("/{id}")
    public String deleteEquipmentById(@PathVariable Long id) {
        return equipmentService.deleteEquipmentById(id);
    }

    @Operation(description = "Searching equipment by query")
    @GetMapping(value = "/search")
    public PageRs<List<EquipmentRs>> searchEquipmentRsByQuery(@ParameterObject @Valid EquipmentSearchRq searchRq,
                                                              @ParameterObject @Valid PageSizeRq req) {
        return equipmentService.searchEquipmentRsByQuery(searchRq, req.getOffset(), req.getLimit());
    }
}