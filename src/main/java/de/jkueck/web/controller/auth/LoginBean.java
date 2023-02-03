package de.jkueck.web.controller.auth;

import de.jkueck.web.controller.BaseController;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
@RequestScoped
public class LoginBean extends BaseController {

    @Inject
    private SecurityContext securityContext;

    @Getter
    @Setter
    private String username = "jankuek@gmail.com";

    @Getter
    @Setter
    private String password = "Ollikahn2";

    public void login() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();

        Credential credential = new UsernamePasswordCredential(this.username, new Password(this.password));

        AuthenticationStatus status = this.securityContext.authenticate(getRequest(context), getResponse(context), withParams().credential(credential));

        switch (status) {
            case SUCCESS:
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/app/operations.xhtml");
                break;
            case SEND_FAILURE:
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
                break;
            case SEND_CONTINUE:
                context.responseComplete();
                break;
        }

    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context.getExternalContext().getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

}