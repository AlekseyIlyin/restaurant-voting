package ru.ilin.restvote.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //https://ask-dev.ru/info/37294/strange-jackson-exception-being-thrown-when-serializing-hibernate-object
public class User extends AbstractNamedEntity {

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled = true;

    @Column(name = "role")
    @NotBlank
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votes;

    public User() {
    }

    public User(Integer id, String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
