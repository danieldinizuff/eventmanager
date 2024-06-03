package com.example.eventmanager.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Atividade {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

public Object getNome() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getNome'");
}

public CharSequence getHorario() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHorario'");
}

public Object getLocal() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLocal'");
}

    

}
