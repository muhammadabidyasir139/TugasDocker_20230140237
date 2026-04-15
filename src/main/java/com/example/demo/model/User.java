package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//form untuk menyimpdan data secara temporary
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String nama;
    private String nim;
    private String jenisKelamin;
}