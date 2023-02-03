package de.jkueck.domain;

import de.jkueck.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TablePostDto {

    private PostDto post;

    private boolean isApprovedButtonDisabled;

    private boolean isPublishButtonDisabled;

}
