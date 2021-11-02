package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class ProductRequest {

    @NotNull(message = "Tanggal Tidak Boleh Kosong")
    private Date dateInput;

    @NotNull(message = "Nama Tidak Boleh Kosong")
    private String name;

    @NotNull(message = "Merek Tidak Boleh Kosong")
    private String merek;

    @NotNull(message = "Jumlah Tidak Boleh Kosong")
    private int jumlah;

    @NotNull(message = "Harga Tidak Boleh Kosong")
    private BigInteger harga;

}
