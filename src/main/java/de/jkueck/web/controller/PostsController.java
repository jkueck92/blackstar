package de.jkueck.web.controller;

import de.jkueck.PostDto;
import de.jkueck.domain.TablePostDto;
import de.jkueck.service.WordpressService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class PostsController implements Serializable {

    @Inject
    private WordpressService wordpressService;

    @Getter
    private List<TablePostDto> posts = new ArrayList<>();

    @Getter
    @Setter
    private TablePostDto selectedTablePost;

    @Getter
    @Setter
    private String selectedFilter;

    @PostConstruct
    public void init() {
        this.initInternal(this.wordpressService.getPosts());
    }

    public void filter(ValueChangeEvent event) {
        String newFilter = (String) event.getNewValue();
        if (newFilter.equals("ALL")) {
            this.initInternal(this.wordpressService.getPosts());
        } else {
            this.initInternal(this.wordpressService.getFilteredPosts(newFilter));
        }
    }

    public void publishPost(TablePostDto tablePostDto) {
        this.wordpressService.publishPost(tablePostDto.getPost().getId());
        PrimeFaces.current().ajax().update("form:datatablePosts");
    }

    public void initInternal(List<PostDto> list) {
        this.posts.clear();
        for (PostDto postDto : list) {
            TablePostDto tablePostDto = TablePostDto.builder().post(postDto).build();
            if (postDto.getStatus().equals("NOT_APPROVED")) {
                tablePostDto.setPublishButtonDisabled(Boolean.TRUE);
                tablePostDto.setApprovedButtonDisabled(Boolean.FALSE);
            } else if (postDto.getStatus().equals("APPROVED")) {
                tablePostDto.setPublishButtonDisabled(Boolean.FALSE);
                tablePostDto.setApprovedButtonDisabled(Boolean.TRUE);
            } else {
                tablePostDto.setPublishButtonDisabled(Boolean.TRUE);
                tablePostDto.setApprovedButtonDisabled(Boolean.TRUE);
            }
            this.posts.add(tablePostDto);
        }
    }

}
