package com.toaa.walletcontrol.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DTOPayment {
    @NotNull(message = "*Please provide payment id")
    private Integer id;
    @NotEmpty(message = "*Please provide payment product")
    private String product;
    @NotNull(message = "*Please provide payment category")
    private Integer categoryId;
    @NotNull(message = "*Please provide payment cost")
    private long cost;
    @NotEmpty(message = "*Please provide payment date")
    private String date;
    private String detail;
    @NotNull(message = "*Please provide payment user")
    private Integer userId;
}
