package com.asklora.system.bankaccountsystem;

import com.asklora.system.bankaccountsystem.dto.CreateAccountRequest;
import com.asklora.system.bankaccountsystem.service.AccountManagerService;
import com.asklora.system.bankaccountsystem.validator.UserAccountValidator;
import com.asklora.system.bankmodelorm.entity.UserAccount;
import com.asklora.system.bankmodelorm.exception.RequestException;
import com.asklora.system.bankmodelorm.repository.UserAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class AccountManagerServiceTest {

    @InjectMocks
    AccountManagerService accountManagerService;

    @Mock
    UserAccountRepository userAccountRepository;

    @Mock
    UserAccountValidator userAccountValidator;

    @Test
    public void createNewAccountTest() throws RequestException {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest();
        createAccountRequest.setAddress("address");
        createAccountRequest.setEmail("test@gmail.com");
        createAccountRequest.setInitialDeposit(5000);
        createAccountRequest.setName("name");
        createAccountRequest.setTaxId("123123");
        Mockito.when(userAccountRepository.findUserAccountByAccountNumber(Mockito.anyString())).thenReturn(null);
        accountManagerService.createNewAccount(createAccountRequest);
    }

    @Test
    public void getUserAccountTest() throws RequestException {
        UserAccount userAccount=new UserAccount();
        userAccount.setAccountNumber("1231231");
        userAccount.setBalance(BigDecimal.valueOf(50000));
        userAccount.setUserName("name");
        userAccount.setEmail("test@gmail.com");
        userAccount.setTaxId("123123");
        userAccount.setPhoneNumber("083248477432");
        userAccount.setLivingAddress("address");
        Mockito.when(userAccountRepository.findUserAccountByUserId(33L)).thenReturn(userAccount);
        accountManagerService.getUserAccount("33");
    }

    @Test(expected = RequestException.class)
    public void getUserAccountExceptionInputTest() throws RequestException {
        accountManagerService.getUserAccount("33S");
    }
    @Test(expected = RequestException.class)
    public void getUserAccountExceptionNotFoundTest() throws RequestException {
        accountManagerService.getUserAccount("33");
    }


}
