package com.example.eventmanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Administrador extends Usuario {
    Administrador(){}
    // Propriedades específicas do administrador
}

