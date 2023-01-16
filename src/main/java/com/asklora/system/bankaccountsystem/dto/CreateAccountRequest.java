package com.asklora.system.bankaccountsystem.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CreateAccountRequest {
    @NotNull(message = "Parameter name can not be empty or null")
    private String name;
    @NotNull(message = "Parameter initialDeposit can not be empty or null")
    private Integer initialDeposit;
    @NotNull(message = "Parameter phoneNumber can not be empty or null")
    private String phoneNumber;
    @NotNull(message = "Parameter taxId can not be empty or null")
    private String taxId;
    @NotNull(message = "Parameter email can not be empty or null")
    private String email;
    @NotNull(message = "Parameter address can not be empty or null")
    private String address;
}
