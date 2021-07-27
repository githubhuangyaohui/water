package com.whut.water.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class History {
    private Integer hid;
    private Customer customer;
    private Worker worker;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendWaterTime;

    private Integer sendWaterCount;
}
