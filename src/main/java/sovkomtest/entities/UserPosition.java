package sovkomtest.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity(name = "User_Position")
@Getter
@Setter
public class UserPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private Position position;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private MyUser myUser;
}
