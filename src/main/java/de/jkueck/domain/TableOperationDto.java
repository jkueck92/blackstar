package de.jkueck.domain;

import de.jkueck.OperationDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableOperationDto {

    private OperationDto operation;

    private boolean isCreatePostButtonDisabled;

}
