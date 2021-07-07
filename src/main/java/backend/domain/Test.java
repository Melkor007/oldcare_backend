package backend.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;
@Validated
@Getter
@Setter
@AllArgsConstructor
public class Test implements Serializable {
    private static final long serialVersionUID = 6130153079603689714L;

    @NotBlank
    private String project_name;

    private String applicant;

    @NotBlank
    private String date1;

    @NotBlank
    private String date2;

    private String content;

    private String version;

    private String tips;
}
