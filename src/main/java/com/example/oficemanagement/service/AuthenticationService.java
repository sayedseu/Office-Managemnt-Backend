package com.example.oficemanagement.service;

import com.example.oficemanagement.exception.ResourceAlreadyExistException;
import com.example.oficemanagement.exception.ResourceNotFoundException;
import com.example.oficemanagement.model.AdminToken;
import com.example.oficemanagement.model.Information;
import com.example.oficemanagement.model.UserToken;
import com.example.oficemanagement.repository.AdminTokenRepository;
import com.example.oficemanagement.repository.InformationRepository;
import com.example.oficemanagement.repository.UserTokenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private UserTokenRepository userTokenRepository;
    private AdminTokenRepository adminTokenRepository;
    private InformationRepository informationRepository;

    public AuthenticationService(UserTokenRepository userTokenRepository, AdminTokenRepository adminTokenRepository, InformationRepository informationRepository) {
        this.userTokenRepository = userTokenRepository;
        this.adminTokenRepository = adminTokenRepository;
        this.informationRepository = informationRepository;
    }

    public UserToken createUser(UserToken userToken) throws ResourceAlreadyExistException {
        Optional<UserToken> optionalUserToken = userTokenRepository.findById(userToken.getId());
        if (optionalUserToken.isPresent()) {
            throw new ResourceAlreadyExistException(userToken.getId());
        } else {
            return userTokenRepository.save(userToken);
        }
    }

    public AdminToken createAdmin(AdminToken adminToken) throws ResourceAlreadyExistException {
        Optional<AdminToken> optionalAdminToken = adminTokenRepository.findById(adminToken.getId());
        if (optionalAdminToken.isPresent()) {
            throw new ResourceAlreadyExistException(adminToken.getId());
        } else {
            return adminTokenRepository.save(adminToken);
        }
    }

    public UserToken authUser(String id, String password) throws ResourceNotFoundException {
        Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);
        if (optionalUserToken.isPresent() && optionalUserToken.get().getPassword().equals(password)) {
            return optionalUserToken.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public AdminToken authAdmin(String id, String password) throws ResourceNotFoundException {
        Optional<AdminToken> optionalAdminToken = adminTokenRepository.findById(id);
        if (optionalAdminToken.isPresent() && optionalAdminToken.get().getPassword().equals(password)) {
            return optionalAdminToken.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public UserToken retrieveUserById(String id) throws ResourceNotFoundException {
        Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);
        if (optionalUserToken.isPresent()) {
            return optionalUserToken.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public List<UserToken> retrieveAll() throws ResourceNotFoundException {
        List<UserToken> userTokenList = userTokenRepository.findAll();
        if (userTokenList.size() == 0) {
            throw new ResourceNotFoundException("empty list");
        } else {
            return userTokenList;
        }
    }

    public boolean deleteUserBuId(String id) throws ResourceNotFoundException {
        Optional<UserToken> optionalUserToken = userTokenRepository.findById(id);
        Optional<Information> optionalInformation = informationRepository.findById(id);
        if (optionalUserToken.isPresent()) {
            userTokenRepository.deleteById(id);
            if (optionalInformation.isPresent()) {
                informationRepository.deleteById(id);
            }
            return true;
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
