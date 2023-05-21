package sovkomtest.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sovkomtest.entities.Position;

@Repository
public interface PositionRepo extends JpaRepository<Position, Integer> {
    Position findByNamePosition(String positionName);
}
