package com.example.oficemanagement.service;

import com.example.oficemanagement.exception.ResourceNotFoundException;
import com.example.oficemanagement.model.Information;
import com.example.oficemanagement.repository.InformationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BackendService {
    private InformationRepository informationRepository;

    public BackendService(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    public Information insert(Information data) {
        Optional<Information> optionalInformation = informationRepository.findById(data.getId());
        if (optionalInformation.isPresent()) {
            Information information = optionalInformation.get();
            information.setAssignedTask(data.getAssignedTask());
            information.setCurrentLocation(data.getCurrentLocation());
            information.setDesignation(data.getDesignation());
            information.setResponsibility(data.getResponsibility());
            information.setCurrentLocation(data.getCurrentLocation());
            return informationRepository.save(information);
        } else {
            return informationRepository.save(data);
        }
    }

    public Information retrieveById(String id) throws ResourceNotFoundException {
        Optional<Information> optionalInformation = informationRepository.findById(id);
        if (optionalInformation.isPresent()) {
            return optionalInformation.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<Information> retrieveAll() throws ResourceNotFoundException {
        List<Information> informationList = informationRepository.findAll();
        if (informationList.size() == 0) {
            throw new ResourceNotFoundException("empty list");
        } else {
            return informationList;
        }
    }
}
