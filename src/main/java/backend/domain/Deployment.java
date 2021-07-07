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
public class Deployment implements Serializable {
    private static final long serialVersionUID = -1359375705096513646L;

    @NotBlank
    private String deployment_name;

    private String applicant;

    private String environment;

    private String date1;

    private String date2;

    private String reason;

}
