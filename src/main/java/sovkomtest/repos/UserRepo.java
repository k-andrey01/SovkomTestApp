package sovkomtest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sovkomtest.entities.MyUser;
import sovkomtest.entities.Position;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Integer> {
    List<MyUser> findByPosition(Position position);
    Optional<MyUser> findByEmail(String email);
    Optional<MyUser> findByPhoneNumber(String phoneNumber);
}