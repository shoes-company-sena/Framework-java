package com.shoescompany.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDTO<T> {


    public static final String DATA_SAVED = "Datos guardados de: ";
    public static final String DATA_UPDATED = "Datos actualizados de: ";


    private String message;
    private boolean state;
    private T data;
}
