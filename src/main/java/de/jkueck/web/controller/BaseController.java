package de.jkueck.web.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public abstract class BaseController implements Serializable {

    public String getRequestParameter(String parameterName) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getParameter(parameterName);
    }

    public Long getRequestParameterAsLong(String parameter) {
        return Long.parseLong(this.getRequestParameter(parameter));
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void addInfoMessage(String summary, String detail) {
        this.addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }

    public void addWarnMessage(String summary, String detail) {
        this.addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }

    public void addErrorMessage(String summary, String detail) {
        this.addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }

    public void addFatalMessage(String summary, String detail) {
        this.addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
    }

    public String facesRedirect(String page) {
        return "/app/" + page + "?faces-redirect=true";
    }

}
