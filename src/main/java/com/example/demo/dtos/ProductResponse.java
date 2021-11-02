package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private Date dateInput;
    private String name;
    private String merek;
    private BigInteger harga;
    private int jumlah;
}
