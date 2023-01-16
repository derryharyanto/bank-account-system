package com.asklora.system.bankaccountsystem.dto;

import com.asklora.system.bankmodelorm.dto.ApiResponse;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class CreateAccountResponse extends CreateAccountRequest {
    private Long userId;
    private String accountNumber;
    private ApiResponse response;
}
