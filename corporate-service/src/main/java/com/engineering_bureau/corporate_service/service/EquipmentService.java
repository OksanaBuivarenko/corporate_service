package com.engineering_bureau.corporate_service.service;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentRq;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentSearchRq;
import com.engineering_bureau.corporate_service.dto.response.storage.EquipmentRs;
import com.engineering_bureau.corporate_service.entity.storage.Equipment;

import java.util.List;

public interface EquipmentService {

    Equipment getEquipmentById(Long id);

    PageRs<List<EquipmentRs>> getAllEquipmentRs(Integer offset, Integer limit);

    EquipmentRs getEquipmentRsById(Long id);

    EquipmentRs createEquipment(EquipmentRq equipmentRq);

    EquipmentRs updateEquipmentById(Long id, EquipmentRq equipmentRq);

    String deleteEquipmentById(Long id);

    PageRs<List<EquipmentRs>> searchEquipmentRsByQuery(EquipmentSearchRq searchRq, Integer offset, Integer limit);

}
