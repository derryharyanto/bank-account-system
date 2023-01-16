package com.asklora.system.bankaccountsystem.service;

import com.asklora.system.bankaccountsystem.dto.CreateAccountRequest;
import com.asklora.system.bankaccountsystem.dto.CreateAccountResponse;
import com.asklora.system.bankaccountsystem.dto.GetAccountResponse;
import com.asklora.system.bankmodelorm.exception.RequestException;
import com.asklora.system.bankaccountsystem.validator.UserAccountValidator;
import com.asklora.system.bankmodelorm.entity.UserAccount;
import com.asklora.system.bankmodelorm.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Date;

@Service
public class AccountManagerService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserAccountValidator userAccountValidator;

    @Transactional
    public CreateAccountResponse createNewAccount(CreateAccountRequest createAccountRequest) throws RequestException {

        userAccountValidator.isValidAccountRequest(createAccountRequest);

        UserAccount userAccount = new UserAccount();
        userAccount.setBalance(BigDecimal.valueOf(createAccountRequest.getInitialDeposit()));
        userAccount.setEmail(createAccountRequest.getEmail());
        userAccount.setUserName(createAccountRequest.getName());
        userAccount.setLivingAddress(createAccountRequest.getAddress());
        userAccount.setPhoneNumber(createAccountRequest.getPhoneNumber());
        userAccount.setUpdatedDtime(new Date());
        userAccount.setCreatedDtime(new Date());
        userAccount.setTaxId(createAccountRequest.getTaxId());
        userAccount.setAccountNumber(generateAccountNumbers());

        userAccountRepository.save(userAccount);


        CreateAccountResponse createAccountResponse = new CreateAccountResponse();
        createAccountResponse.setAccountNumber(userAccount.getAccountNumber());
        createAccountResponse.setAddress(userAccount.getLivingAddress());
        createAccountResponse.setUserId(userAccount.getUserId());
        createAccountResponse.setEmail(userAccount.getEmail());
        createAccountResponse.setName(userAccount.getUserName());
        createAccountResponse.setInitialDeposit(userAccount.getBalance().intValue());
        createAccountResponse.setPhoneNumber(userAccount.getPhoneNumber());
        createAccountResponse.setTaxId(userAccount.getTaxId());

        return createAccountResponse;
    }

    private String generateAccountNumbers(){
        SecureRandom random= new SecureRandom();
        String generate="";
        while (generate.length()<13){
            generate=generate+random.nextInt(10);
        }
        if (userAccountRepository.findUserAccountByAccountNumber(generate)!=null){
            generateAccountNumbers();
        }
        return generate;
    }

    public GetAccountResponse getUserAccount(String userId) throws RequestException {
        if (!userId.matches("^[0-9]*$")){
            throw new RequestException("User must only contain numbers");
        }
        UserAccount userAccount = userAccountRepository.findUserAccountByUserId(Long.valueOf(userId));
        if (userAccount==null){
            throw new RequestException("User ID not found");
        }
        GetAccountResponse response = new GetAccountResponse();
        response.setEmail(userAccount.getEmail());
        response.setPhoneNumber(userAccount.getEmail());
        response.setAddress(userAccount.getLivingAddress());
        response.setTotalBalance(userAccount.getBalance().intValue());
        response.setName(userAccount.getUserName());
        return response;
    }
}
