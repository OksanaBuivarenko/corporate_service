package com.engineering_bureau.corporate_service.service.impl.storage;

import com.engineering_bureau.corporate_service.dto.PageRs;
import com.engineering_bureau.corporate_service.dto.request.storage.ComponentRq;
import com.engineering_bureau.corporate_service.dto.response.storage.ComponentRs;
import com.engineering_bureau.corporate_service.entity.storage.Component;
import com.engineering_bureau.corporate_service.exception.ObjectAlreadyExistsException;
import com.engineering_bureau.corporate_service.mapper.storage.ComponentMapper;
import com.engineering_bureau.corporate_service.repository.storage.ComponentRepository;
import com.engineering_bureau.corporate_service.service.ComponentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {

    private final ComponentRepository componentRepository;

    private final ComponentMapper componentMapper;

    @Override
    public Component getComponentById(Long id) {
        return componentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Component with id " + id + " not found"));
    }

    @Override
    public PageRs<List<ComponentRs>> getAllComponentRs(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return PageRs.<List<ComponentRs>>builder()
                .offset(offset)
                .limit(limit)
                .data(componentRepository.findAll(nextPage).stream().map(component -> componentMapper.toDto(component))
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public ComponentRs getComponentRsById(Long id) {
        return componentMapper.toDto(getComponentById(id));
    }

    @Override
    public ComponentRs createComponent(ComponentRq componentRq) {
        if (componentRepository.existsByName(componentRq.getName())) {
            throw new ObjectAlreadyExistsException("Component with name " + componentRq.getName() + "already exists");
        }
        Component component = componentMapper.toEntity(componentRq);
        Component saveComponent = componentRepository.save(component);
        return componentMapper.toDto(saveComponent);
    }

    @Override
    public ComponentRs updateCompanyById(Long id, ComponentRq componentRq) {
        Component component = getComponentById(id);
        if (componentRq.getName()!=null && !componentRq.getName().trim().isEmpty()) {
            component.setName(componentRq.getName());
        }
        if (componentRq.getCount()!=null) {
            component.setCount(componentRq.getCount());
        }
        if (componentRq.getDescription()!=null && !componentRq.getDescription().trim().isEmpty()) {
            component.setDescription(componentRq.getDescription());
        }
        if (componentRq.getStoragePlace()!=null && !componentRq.getStoragePlace().trim().isEmpty()) {
            component.setStoragePlace(componentRq.getStoragePlace());
        }

        Component updateComponent = componentRepository.save(component);
        return componentMapper.toDto(updateComponent);
    }

    @Override
    public String deleteComponentById(Long id) {
        componentRepository.delete(getComponentById(id));
        String deleteMessage = "Component with id " + id + "delete success";
        log.info(deleteMessage);
        return  deleteMessage;
    }

    @Override
    public Component getComponentByName(String name) {
        return componentRepository.findByName(name).orElseThrow(() ->
                new EntityNotFoundException("Component with name " + name + " not found"));
    }
}
