package de.jkueck.web.controller;

import de.jkueck.OperationDto;
import de.jkueck.WordpressCreatePostDto;
import de.jkueck.service.OperationService;
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
import java.time.format.DateTimeFormatter;

@Named
@ViewScoped
public class CreatePostController extends BaseController {

    @Inject
    private OperationService operationService;

    @Inject
    private WordpressService wordpressService;

    @Getter
    @Setter
    private long operationId;

    @Getter
    private OperationDto operation;

    @Getter
    @Setter
    private WordpressCreatePostDto createPost;

    @PostConstruct
    public void init() {
        this.operationId = this.getRequestParameterAsLong("id");
        this.operation = this.operationService.getOperationById(this.operationId);

        this.createPost = WordpressCreatePostDto.builder().build();

        this.createPost.setLocation(this.operation.getLocation());
        this.createPost.setTitle(this.operation.getKeywordText());
        this.createPost.setKeyword(this.operation.getKeyword());
        this.createPost.setAlarmTimestamp(this.operation.getTimestamp().format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm")));
        this.createPost.setPlannedReleaseAt(this.getOperation().getTimestamp().plusDays(1));
        this.createPost.setOperationId(this.operationId);
    }

    public void actionOnClickSave() {
        this.wordpressService.createPost(this.createPost);
        this.addInfoMessage("Post", "Post is created.");
    }

    public String onClickBack() {
        return this.facesRedirect(Pages.OPERATIONS.getName());
    }

}
