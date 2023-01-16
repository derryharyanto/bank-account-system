package com.asklora.system.bankaccountsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetAccountResponse {
    private Integer totalBalance;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
}
