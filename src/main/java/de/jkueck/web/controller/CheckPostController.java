package de.jkueck.web.controller;

import de.jkueck.CheckPostDto;
import de.jkueck.PostDto;
import de.jkueck.service.BaseService;
import de.jkueck.service.WordpressService;
import de.jkueck.web.common.Pages;
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
public class CheckPostController extends BaseController {

    @Inject
    private WordpressService wordpressService;

    @Getter
    @Setter
    private long postId;

    @Getter
    private PostDto post;

    @Getter
    @Setter
    private CheckPostDto checkPost;

    @PostConstruct
    public void init() {
        this.postId = this.getRequestParameterAsLong("id");
        this.post = this.wordpressService.getPostById(this.postId);

        this.checkPost = CheckPostDto.builder().build();

        this.checkPost.setPostId(this.postId);
        this.checkPost.setOldText(this.post.getText());
        this.checkPost.setNewText(this.post.getText());
    }

    public void approvePost() {
        this.wordpressService.approvePost(this.checkPost);
        this.addInfoMessage("Post", "Post is approved.");
    }

    public String onClickBack() {
        return this.facesRedirect(Pages.POSTS.getName());
    }

}
