package com.example.demo.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RichiestaRequestDTO {
    
    private Integer idModello;
    private Integer idColore;
    private List<Integer> idOptional;
    
    private Integer idCliente;   
    private String partitaIva;   
}