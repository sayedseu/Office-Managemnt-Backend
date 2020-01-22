package com.example.oficemanagement.controller;

import com.example.oficemanagement.exception.ResourceNotFoundException;
import com.example.oficemanagement.model.Information;
import com.example.oficemanagement.service.BackendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/info")
public class BackendController {
    private BackendService service;

    public BackendController(BackendService service) {
        this.service = service;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Information> insert(@RequestBody Information information) {
        try {
            Information insert = service.insert(information);
            return ResponseEntity.status(HttpStatus.OK).body(insert);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Information> retrieveById(@PathVariable(value = "id") String id) {
        try {
            Information information = service.retrieveById(id);
            return ResponseEntity.status(HttpStatus.OK).body(information);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Information>> retrieveById() {
        try {
            List<Information> informationList = service.retrieveAll();
            return ResponseEntity.status(HttpStatus.OK).body(informationList);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
