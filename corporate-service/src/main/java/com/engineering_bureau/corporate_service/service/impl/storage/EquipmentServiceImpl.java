package com.engineering_bureau.corporate_service.service.impl.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentRq;
import com.engineering_bureau.corporate_service.dto.request.storage.EquipmentSearchRq;
import com.engineering_bureau.corporate_service.dto.response.storage.EquipmentRs;
import com.engineering_bureau.corporate_service.entity.storage.Equipment;
import com.engineering_bureau.corporate_service.entity.storage.Equipment_;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.storage.EquipmentMapper;
import com.engineering_bureau.corporate_service.repository.specification.Specs;
import com.engineering_bureau.corporate_service.repository.storage.EquipmentRepository;
import com.engineering_bureau.corporate_service.service.EquipmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final EquipmentMapper equipmentMapper;

    @Override
    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Equipment with id " + id + " not found"));
    }

    @Override
    public PageRs<List<EquipmentRs>> getAllEquipmentRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<EquipmentRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(equipmentRepository.findAll(nextPage).stream().map(equipmentMapper::toDto)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public EquipmentRs getEquipmentRsById(Long id) {
        return equipmentMapper.toDto(getEquipmentById(id));
    }

    @Override
    public EquipmentRs createEquipment(EquipmentRq equipmentRq) {
        if(equipmentRepository.existsByName(equipmentRq.getName())) {
            throw new ObjectAlreadyExistsException("Equipment with name " + equipmentRq.getName() + "already exists");
        }
        Equipment equipment = equipmentMapper.toEntity(equipmentRq);
        Equipment saveEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(saveEquipment);
    }

    @Override
    public EquipmentRs updateEquipmentById(Long id, EquipmentRq equipmentRq) {
        Equipment equipment = getEquipmentById(id);
        if (equipmentRq.getName()!=null && !equipmentRq.getName().trim().isEmpty()) {
            equipment.setName(equipmentRq.getName());
        }
        if (equipmentRq.getDescription()!=null && !equipmentRq.getDescription().trim().isEmpty()) {
            equipment.setDescription(equipmentRq.getDescription());
        }
        if (equipmentRq.getStoragePlace()!=null && !equipmentRq.getStoragePlace().trim().isEmpty()) {
            equipment.setStoragePlace(equipmentRq.getStoragePlace());
        }
        if (equipmentRq.getCount()!=null) {
            equipment.setCount(equipmentRq.getCount());
        }
        Equipment updateEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDto(updateEquipment);
    }

    @Override
    public String deleteEquipmentById(Long id) {
        equipmentRepository.delete(getEquipmentById(id));
        String deleteMessage = "Equipment with id " + id + "delete success";
        log.info(deleteMessage);
        return  deleteMessage;
    }

    @Override
    public PageRs<List<EquipmentRs>> searchEquipmentRsByQuery(EquipmentSearchRq searchRq, Integer offset, Integer limit) {
        List<EquipmentRs> resultList =  equipmentRepository.findAll(
                Specification.where(Specs.eq(Equipment_.id, searchRq.getId()))
                        .and(Specs.like(Equipment_.name, searchRq.getName()))
                        .and(Specs.like(Equipment_.name, searchRq.getDescription()))
                        .and(Specs.like(Equipment_.storagePlace, searchRq.getStoragePlace())),
                        PageRequest.of(offset, limit))
                .stream().map(equipmentMapper::toDto).collect(Collectors.toList());

        return PageRs.<List<EquipmentRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(resultList)
                .build();
    }
}