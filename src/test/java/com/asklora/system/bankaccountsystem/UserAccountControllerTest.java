package com.asklora.system.bankaccountsystem;

import com.asklora.system.bankaccountsystem.controller.UserAccountController;
import com.asklora.system.bankaccountsystem.dto.CreateAccountRequest;
import com.asklora.system.bankaccountsystem.dto.CreateAccountResponse;
import com.asklora.system.bankaccountsystem.dto.GetAccountResponse;
import com.asklora.system.bankaccountsystem.service.AccountManagerService;
import com.asklora.system.bankmodelorm.exception.RequestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserAccountControllerTest {


    @InjectMocks
    UserAccountController userAccountController;

    @Mock
    AccountManagerService accountManagerService;

    @Test
    public void createAccountTest() throws RequestException {
        CreateAccountRequest request = new CreateAccountRequest();
        Mockito.when(accountManagerService.createNewAccount(request)).thenReturn(new CreateAccountResponse());
        userAccountController.createAccount(request);
    }

    @Test
    public void createAccountExceptionTest() throws RequestException {
        CreateAccountRequest request = new CreateAccountRequest();
        Mockito.when(accountManagerService.createNewAccount(request)).thenThrow(new RequestException("e"));
        userAccountController.createAccount(request);
    }
    @Test
    public void getUserTest() throws RequestException {
        Mockito.when(accountManagerService.getUserAccount("33")).thenReturn(new GetAccountResponse());
        userAccountController.getUserAccount("33");
    }

    @Test
    public void getUserExceptionTest() throws RequestException {
        Mockito.when(accountManagerService.getUserAccount("33")).thenThrow(new RequestException("re"));
        userAccountController.getUserAccount("33");
    }

}


