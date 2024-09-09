package com.shoescompany.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {


    @NotNull(message = "La categoría no puede ser nula")
    @NotBlank(message = "La categoría no puede estar en blanco")
    @Size(min = 1, max = 50, message = "La categoría debe tener entre 1 y 50 caracteres")
    private String category;

    @NotBlank(message = "La imagen de la categoría no puede estar en blanco")
    @Size(min = 1, max = 255, message = "La imagen de la categoría debe tener entre 1 y 255 caracteres")
    private String image;
}
