package de.jkueck.web.auth;

import de.jkueck.LoginDto;
import de.jkueck.UserDto;
import de.jkueck.service.AuthenticationService;

import static java.util.Arrays.asList;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class TestIdentityStore implements IdentityStore {

    @Inject
    private AuthenticationService authenticationService;

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        LoginDto loginDto = LoginDto.builder().email(usernamePasswordCredential.getCaller()).password(usernamePasswordCredential.getPasswordAsString()).build();

        UserDto userDto = this.authenticationService.login(loginDto);
        if (userDto != null) {

            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", userDto);

            return new CredentialValidationResult(userDto.getEmail(), new HashSet<>(asList("ADMIN", "USER")));
        }

        return INVALID_RESULT;
    }

}