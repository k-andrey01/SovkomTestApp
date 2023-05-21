package sovkomtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sovkomtest.dto.UserPositionDto;
import sovkomtest.entities.UserPosition;
import sovkomtest.repos.UserPositionRepo;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/commonTable")
public class UserPositionController {
    @Autowired
    UserPositionRepo userPositionRepo;

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<UserPositionDto> getAllUserPositions() {
        Iterable<UserPosition> userPositions = userPositionRepo.findAll();
        List<UserPositionDto> userPositionDtos = new ArrayList<>();

        for (UserPosition userPosition : userPositions) {
            UserPositionDto dto = new UserPositionDto();
            dto.setNamePosition(userPosition.getPosition().getNamePosition());
            dto.setSurname(userPosition.getMyUser().getSurname());
            dto.setName(userPosition.getMyUser().getName());
            dto.setMiddleName(userPosition.getMyUser().getMiddleName());
            dto.setBirthdate(userPosition.getMyUser().getBirthdate());
            dto.setEmail(userPosition.getMyUser().getEmail());
            dto.setPhoneNumber(userPosition.getMyUser().getPhoneNumber());

            userPositionDtos.add(dto);
        }

        return userPositionDtos;
    }

}
