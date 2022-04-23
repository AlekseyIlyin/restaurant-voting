package ru.ilin.restvote.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User extends AbstractNamedEntity {

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "role")
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;
}
