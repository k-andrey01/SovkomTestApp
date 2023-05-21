package sovkomtest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sovkomtest.entities.UserPosition;

@Repository
public interface UserPositionRepo extends JpaRepository<UserPosition, Integer> {

}
