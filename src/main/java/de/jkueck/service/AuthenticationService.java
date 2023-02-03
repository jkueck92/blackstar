package de.jkueck.service;

import de.jkueck.LoginDto;
import de.jkueck.UserDto;
import org.apache.http.client.methods.CloseableHttpResponse;

import javax.ejb.Stateless;

@Stateless
public class AuthenticationService extends BaseService {

    public UserDto getUserByEmail(String email) {
        try {
            CloseableHttpResponse response = this.getHttpService().sendGet("http://localhost:8082/api/v1/auth/users/" + email);
            if (this.getHttpService().checkResponse200(response)) {
                return this.getObjectMapper().readValue(this.getHttpService().getResponseAsString(response), UserDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDto login(LoginDto loginDto) {
        try {
            String json = this.getObjectMapper().writeValueAsString(loginDto);
            CloseableHttpResponse response = this.getHttpService().sendPost("http://localhost:8082/api/v1/auth/login", json);
            if (this.getHttpService().checkResponse200(response)) {
                return this.getObjectMapper().readValue(this.getHttpService().getResponseAsString(response), UserDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
