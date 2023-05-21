package sovkomtest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String namePosition;
    private String description;

    @OneToMany
    @JsonIgnore
    private List<MyUser> users;
    @OneToMany
    @JsonIgnore
    private List<UserPosition> userPositions;
}
