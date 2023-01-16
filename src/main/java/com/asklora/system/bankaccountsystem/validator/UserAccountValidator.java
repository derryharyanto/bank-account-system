package com.asklora.system.bankaccountsystem.validator;

import com.asklora.system.bankaccountsystem.dto.CreateAccountRequest;
import com.asklora.system.bankmodelorm.exception.RequestException;
import com.asklora.system.bankmodelorm.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserAccountValidator {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public void isValidAccountRequest(CreateAccountRequest request) throws RequestException {
        String userPhoneNumber = userAccountRepository.findUserAccountByPhoneNumber(request.getPhoneNumber());
        String userTaxId = userAccountRepository.findUserAccountByTaxId(request.getTaxId());
        if (request.getName().length()>=25 || request.getName().length()<=0){
            throw new RequestException( "Name should have at most 25 characters");
        }
        else if(!request.getName().matches("^[a-zA-Z ]*$")){
            throw new RequestException( "Name must only contain alphabet");
        }
        else if (request.getInitialDeposit()< 5000){
            throw new RequestException( "Initial Deposit must have at least 5000");
        } else if (userPhoneNumber!=null) {
            throw new RequestException( "Phone Number is already exist");
        }
        else if (request.getTaxId().length()!=6){
            throw new RequestException( "Tax ID should have be a unique 6 digit number ");
        }
        else if (userTaxId!=null){
            throw new RequestException( "Tax ID is already exist");
        }
        else if(!request.getTaxId().matches("^[0-9]*$")){
            throw new RequestException("Tax ID must only contain numbers");
        }
        else if (!request.getEmail().matches("^[\\w\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            throw new RequestException("Please input the correct email");
        }
    }

}
