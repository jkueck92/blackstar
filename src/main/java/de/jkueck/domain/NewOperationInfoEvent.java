package de.jkueck.domain;

import de.jkueck.NewOperationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewOperationInfoEvent {

    private NewOperationInfo newOperationInfo;

}
