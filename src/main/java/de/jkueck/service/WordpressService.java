package de.jkueck.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.jkueck.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
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
import java.util.Collections;
import java.util.List;

@Stateless
public class WordpressService implements Serializable {

    public PostDto getPostById(long id) {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8082/api/v1/posts/" + id);
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpGet);

            try {

                HttpEntity entity1 = closeableHttpResponse.getEntity();
                String response = EntityUtils.toString(entity1);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper.readValue(response, PostDto.class);

            } finally {
                closeableHttpResponse.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PostDto.builder().build();
    }

    public void approvePost(CheckPostDto checkPostDto) {
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost("http://localhost:8082/api/v1/posts/approved");

            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String json = objectMapper.writeValueAsString(checkPostDto);

            StringEntity httpEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

            httpPost.setEntity(httpEntity);

            CloseableHttpResponse response = httpclient.execute(httpPost);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void publishPost(long id) {

        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost("http://localhost:8082/api/v1/posts/" + id + "/published");

            CloseableHttpResponse response = httpclient.execute(httpPost);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PostDto> getFilteredPosts(String filter) {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8082/api/v1/posts/filter/" + filter);
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpGet);

            try {

                HttpEntity entity1 = closeableHttpResponse.getEntity();
                String response = EntityUtils.toString(entity1);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper.readValue(response, new TypeReference<>() {});

            } finally {
                closeableHttpResponse.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<PostDto> getPosts() {
        try {

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://localhost:8082/api/v1/posts");
            CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpGet);

            try {

                HttpEntity entity1 = closeableHttpResponse.getEntity();
                String response = EntityUtils.toString(entity1);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                return objectMapper.readValue(response, new TypeReference<>() {});

            } finally {
                closeableHttpResponse.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public void createPost(WordpressCreatePostDto createPostDto) {

        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();

            HttpPost httpPost = new HttpPost("http://localhost:8082/api/v1/posts");

            httpPost.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            String json = objectMapper.writeValueAsString(createPostDto);

            StringEntity httpEntity = new StringEntity(json, ContentType.APPLICATION_JSON);

            httpPost.setEntity(httpEntity);

            CloseableHttpResponse response = httpclient.execute(httpPost);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
