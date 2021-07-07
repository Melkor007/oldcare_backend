package backend.domain;
import java.io.Serializable;
import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
@Validated
@Getter
@Setter
@AllArgsConstructor
public class Project implements Serializable{
    private static final long serialVersionUID = -6705610153517716766L;

    @NotBlank
    private String project_id;

    @NotBlank
    @Size(max = 20)
    private String project_name;

    @Size(max=5)
    private String applicant;

    @Size(max=10)
    private String phase;

    private String level;

    private String type;

    private String money;

    private String date1;

    private String date2;

    private String intro;

    private String innovate;

    private String direction;

}
