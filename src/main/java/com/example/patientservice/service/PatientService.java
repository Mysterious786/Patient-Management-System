package com.example.patientservice.service;


import com.example.patientservice.dto.PatientRequestDTO;
import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.exception.EmailAlreadyExistsException;
import com.example.patientservice.mapper.PatientMapper;
import com.example.patientservice.model.Patient;
import com.example.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private  PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }


    public List<PatientResponseDTO> getPatients() {
        return patientRepository.findAll()
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail()))
        {
            throw new EmailAlreadyExistsException("A patient with this email already exists!" + patientRequestDTO.getEmail());
        }
        return PatientMapper.toDTO(newPatient);
    }

}
