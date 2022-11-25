package org.demo.domain.dto;

import org.demo.domain.Url;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class UrlDto {
    private String shortForm;
    private String longForm;

    public UrlDto(Url url) {
        this.shortForm = url.getShortForm();
        this.longForm = url.getLongForm();
    }
}
