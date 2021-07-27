package com.whut.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Worker {

    private Integer wid;
    private String workerName;
    private Integer workerSalary;
    private Double workerMoney;
    private String workerImage;
}
