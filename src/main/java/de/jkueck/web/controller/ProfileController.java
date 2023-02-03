package de.jkueck.web.controller;

import de.jkueck.CheckPostDto;
import de.jkueck.PostDto;
import de.jkueck.UserDto;
import de.jkueck.service.UserService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@ViewScoped
public class ProfileController implements Serializable {

    @Inject
    private UserService userService;

    @Getter
    @Setter
    private UserDto user;

    @PostConstruct
    public void init() {
        this.user = (UserDto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
    }

    public void onClickSave() {
        this.userService.updateUser(this.user);
    }

}
