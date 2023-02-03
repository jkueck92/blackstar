package de.jkueck.web.controller;

import de.jkueck.OperationDto;
import de.jkueck.service.OperationService;
import de.jkueck.domain.TableOperationDto;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class OperationsController extends BaseController {

    @Inject
    private OperationService operationService;

    @Getter
    @Setter
    private OperationDto selectedOperation;

    @Getter
    private List<TableOperationDto> operations = new ArrayList<>();

    @PostConstruct
    public void init() {
        this.operations.clear();
        for (OperationDto operationDto : this.operationService.getOperations()) {
            TableOperationDto tableOperationDto = TableOperationDto.builder().operation(operationDto).build();

            if (operationDto.isHasPost()) {
                tableOperationDto.setCreatePostButtonDisabled(Boolean.TRUE);
            } else {
                tableOperationDto.setCreatePostButtonDisabled(Boolean.FALSE);
            }

            this.operations.add(tableOperationDto);
        }
    }

}
