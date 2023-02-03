package de.jkueck.service;

import com.fasterxml.jackson.core.type.TypeReference;
import de.jkueck.OperationDto;
import org.apache.http.client.methods.CloseableHttpResponse;

import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;

@Stateless
public class OperationService extends BaseService {

    public OperationDto getOperationById(long id) {
        try {
            CloseableHttpResponse response = this.getHttpService().sendGet("http://localhost:8082/api/v1/operations/" + id);
            if (this.getHttpService().checkResponse200(response)) {
                return this.getObjectMapper().readValue(this.getHttpService().getResponseAsString(response), OperationDto.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OperationDto.builder().build();
    }

    public List<OperationDto> getOperations() {
        try {
            CloseableHttpResponse response = this.getHttpService().sendGet("http://localhost:8082/api/v1/operations");
            if (this.getHttpService().checkResponse200(response)) {
                return this.getObjectMapper().readValue(this.getHttpService().getResponseAsString(response), new TypeReference<>() {
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}
