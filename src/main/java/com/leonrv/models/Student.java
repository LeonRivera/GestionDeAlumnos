package com.leonrv.models;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
// @Builder
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor




@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{
    static final long serialVersionUID = 3L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Nombre obligatorio")
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String correoElectronico;
    
    @Column
    private Integer age;
    
}
