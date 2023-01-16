package com.asklora.system.bankaccountsystem.controller;

import com.asklora.system.bankaccountsystem.dto.*;
import com.asklora.system.bankaccountsystem.service.AccountManagerService;
import com.asklora.system.bankmodelorm.dto.ApiResponse;
import com.asklora.system.bankmodelorm.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/asklora/api/account/v1")
public class UserAccountController {

    @Autowired
    private AccountManagerService accountManagerService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<CreateAccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest){
        HttpStatus status;
        CreateAccountResponse createAccountResponse=new CreateAccountResponse();
        ApiResponse apiResponse=new ApiResponse();
        try {
            createAccountResponse=accountManagerService.createNewAccount(createAccountRequest);
            status=HttpStatus.OK;
            apiResponse.setMessage(HttpStatus.OK.getReasonPhrase());
            apiResponse.setStatus(HttpStatus.OK.value());
            createAccountResponse.setResponse(apiResponse);
        }
        catch (RequestException re){
            apiResponse.setMessage(re.getMessage());
            apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            status=HttpStatus.BAD_REQUEST;
            createAccountResponse.setResponse(apiResponse);

        }catch (Exception e){
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status=HttpStatus.INTERNAL_SERVER_ERROR;
            createAccountResponse.setResponse(apiResponse);
        }
        return new ResponseEntity(createAccountResponse,status);
    }


    @RequestMapping(value = "/get/{userId}",method = RequestMethod.GET)
    public ResponseEntity<Object> getUserAccount(@PathVariable("userId") String userId){
        HttpStatus status;
        ApiResponse errorResponse=new ApiResponse();
        GetAccountResponse accountResponse;
        try {
            accountResponse=accountManagerService.getUserAccount(userId);
            status=HttpStatus.OK;
        }
        catch (RequestException re){
            errorResponse.setMessage(re.getMessage());
            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            status=HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(errorResponse,status);

        }catch (Exception e){
            errorResponse.setMessage(e.getMessage());
            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            status=HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(errorResponse,status);
        }
        return new ResponseEntity<>(accountResponse,status);
    }
}
