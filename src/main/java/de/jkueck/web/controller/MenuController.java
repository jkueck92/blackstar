package de.jkueck.web.controller;

import de.jkueck.PermissionDto;
import de.jkueck.RoleDto;
import de.jkueck.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class MenuController implements Serializable {

    @Getter
    @Setter
    private UserDto user;

    @PostConstruct
    public void init() {
        this.user = (UserDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public boolean checkPermission(String permissionName) {
        for (PermissionDto permission : this.user.getRole().getPermissions()) {
            if (permission.getName().equals(permissionName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}
