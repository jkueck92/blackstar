package de.jkueck.service;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.Serializable;

@Stateless
public class HttpService implements Serializable {

    public CloseableHttpResponse sendGet(String url) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            return httpclient.execute(httpGet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CloseableHttpResponse sendPost(String url, String value) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httpPost = new HttpPost(url);

            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

            StringEntity httpEntity = new StringEntity(value, ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            return httpclient.execute(httpPost);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkResponse200(CloseableHttpResponse response) {
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String getResponseAsString(CloseableHttpResponse response) throws IOException {
        return EntityUtils.toString(response.getEntity());
    }

}
