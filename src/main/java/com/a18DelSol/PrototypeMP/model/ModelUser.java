package com.a18DelSol.PrototypeMP.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ModelUser {
    @Id @GeneratedValue(strategy= GenerationType.AUTO) private Integer userID;

    private String userName;
    private String userMail;
    private String userPass;
    private LocalDate userBirth;
}
