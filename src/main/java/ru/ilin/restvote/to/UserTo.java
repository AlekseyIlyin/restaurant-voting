package ru.ilin.restvote.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.ilin.restvote.model.Role;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

public class UserTo extends BaseTo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    // https://stackoverflow.com/a/12505165/548473
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @NotBlank
    private Role role;

    public UserTo() {
    }

    public UserTo(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
