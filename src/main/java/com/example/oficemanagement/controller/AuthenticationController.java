package com.example.oficemanagement.controller;

import com.example.oficemanagement.exception.ResourceAlreadyExistException;
import com.example.oficemanagement.exception.ResourceNotFoundException;
import com.example.oficemanagement.model.AdminToken;
import com.example.oficemanagement.model.UserToken;
import com.example.oficemanagement.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {
    private AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping(value = "/admin/new")
    public ResponseEntity<AdminToken> createAdmin(@RequestBody AdminToken adminToken) {
        try {
            AdminToken admin = service.createAdmin(adminToken);
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/user/new")
    public ResponseEntity<UserToken> createAdmin(@RequestBody UserToken userToken) {
        try {
            UserToken user = service.createUser(userToken);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/user/{id}/{pass}")
    public ResponseEntity<UserToken> authUser(@PathVariable(value = "id") String id, @PathVariable(value = "pass") String password) {
        try {
            UserToken userToken = service.authUser(id, password);
            return ResponseEntity.status(HttpStatus.OK).body(userToken);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @PostMapping(value = "/admin")
    public ResponseEntity<AdminToken> authAdmin(@RequestParam String id, @RequestParam String password) {
        try {
            AdminToken adminToken = service.authAdmin(id, password);
            return ResponseEntity.status(HttpStatus.OK).body(adminToken);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserToken> retrieveById(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.retrieveUserById(id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/user/all")
    public ResponseEntity<List<UserToken>> retrieveById() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.retrieveAll());
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/user/delete")
    public ResponseEntity<Boolean> deleteUserById(@RequestParam String id) {
        try {
            boolean deleteUserBuId = service.deleteUserBuId(id);
            return ResponseEntity.status(HttpStatus.OK).body(deleteUserBuId);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
