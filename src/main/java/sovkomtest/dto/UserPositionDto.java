package sovkomtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPositionDto {
    private String surname;
    private String name;
    private String middleName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String email;
    private String phoneNumber;
    private String namePosition;
}