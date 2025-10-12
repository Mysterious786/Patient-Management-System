package com.example.patientservice.controller;


import com.example.patientservice.dto.PatientResponseDTO;
import com.example.patientservice.model.Patient;
import com.example.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    //@Autowired
    private final PatientService patientService;

    // usage of constructor injection instead using autowired....
    public PatientController(PatientService patientService)
    {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients()
    {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }
    @PostMapping
    public ResponseEntity<String> addPatient(@RequestBody Patient patient)
    {
        try{
            patientService.saveNewPatient(patient);
            return new ResponseEntity<>("Patient Added Successfully", HttpStatus.CREATED);

        }catch(Exception e)
        {
            return new ResponseEntity<>("Error saving user", HttpStatus.BAD_REQUEST);
        }
    }
}
