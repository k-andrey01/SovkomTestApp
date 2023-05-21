package sovkomtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sovkomtest.entities.Position;
import sovkomtest.repos.PositionRepo;

import java.util.Optional;

@Controller
@RequestMapping(path = "/position")
public class PositionController {
    @Autowired
    private PositionRepo positionRepo;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewPosition(@RequestParam String namePosition, @RequestParam String description) {
        Position position = new Position();
        position.setNamePosition(namePosition);
        position.setDescription(description);
        positionRepo.save(position);
        return "Сохранено";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Position> getAllPositions() {
        return positionRepo.findAll();
    }

    @GetMapping(path = "select/{id}")
    public @ResponseBody
    Optional<Position> getPositionById(@PathVariable("id") Integer id) {
        return positionRepo.findById(id);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String updatePosition(@PathVariable("id") Integer id, @RequestParam String namePosition,
                          @RequestParam String description) {
        Optional<Position> optionalPosition = positionRepo.findById(id);
        if (optionalPosition.isPresent()) {
            Position position = optionalPosition.get();
            position.setNamePosition(namePosition);
            position.setDescription(description);
            positionRepo.save(position);
            return "Изменено";
        } else {
            return "Должность не найдена";
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deletePosition(@PathVariable("id") Integer id) {
        Optional<Position> optionalPosition = positionRepo.findById(id);
        if (optionalPosition.isPresent()) {
            positionRepo.delete(optionalPosition.get());
            return "Удалено";
        } else {
            return "Должность не найдена";
        }
    }
}
