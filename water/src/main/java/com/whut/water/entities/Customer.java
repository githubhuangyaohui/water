package com.whut.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer cid;
    private String custName;
    private String custMobile;
    private String custAddress;
    private Integer custTicket;
}
