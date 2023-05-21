package sovkomtest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sovkomtest.entities.MyUser;
import sovkomtest.entities.Position;
import sovkomtest.entities.UserPosition;
import sovkomtest.repos.PositionRepo;
import sovkomtest.repos.UserPositionRepo;
import sovkomtest.repos.UserRepo;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PositionRepo positionRepo;
    @Autowired
    private UserPositionRepo userPositionRepo;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^8\\d{10}$";

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String surname, @RequestParam String name,
                      @RequestParam String middleName, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      @RequestParam String email, @RequestParam String phoneNumber, @RequestParam Position position) {

        Optional<MyUser> existingUser = userRepo.findByEmail(email);
        if (existingUser.isPresent()) {
            return "Данный email занят";
        }

        Optional<MyUser> existingUserViaPhone = userRepo.findByPhoneNumber(phoneNumber);
        if (existingUserViaPhone.isPresent()) {
            return "Данный номер телефона занят";
        }

        if (!email.matches(EMAIL_REGEX)) {
            return "Неверный формат email";
        }

        if (!phoneNumber.matches(PHONE_REGEX)) {
            return "Введите номер в формате 8хххххххххх";
        }

        MyUser user = new MyUser();
        user.setName(name);
        user.setSurname(surname);
        user.setMiddleName(middleName);
        user.setBirthdate(birthdate);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPosition(position);
        userRepo.save(user);

        UserPosition userPosition = new UserPosition();
        userPosition.setMyUser(user);
        userPosition.setPosition(position);
        userPositionRepo.save(userPosition);
        return "Сохранено";
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<MyUser> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping(path = "selectById/{id}")
    public @ResponseBody
    Optional<MyUser> getUserById(@PathVariable("id") Integer id) {
        return userRepo.findById(id);
    }

    @GetMapping(path = "selectByPosition/{positionName}")
    public @ResponseBody
    List<MyUser> getUserByPosition(@PathVariable("positionName") String positionName) {
        Position position = positionRepo.findByNamePosition(positionName);
        if (position == null) {
            return Collections.emptyList();
        }

        List<MyUser> users = userRepo.findByPosition(position);
        return users;
    }


    @GetMapping(path = "/select/orderByBirthdate")
    public @ResponseBody
    List<MyUser> getUsersOrderedByBirthdate() {
        return userRepo.findAll(Sort.by("birthdate"));
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String updateUser(@PathVariable("id") Integer id, @RequestParam String surname,
                      @RequestParam String name, @RequestParam String middleName,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
                      @RequestParam String email, @RequestParam String phoneNumber, @RequestParam Position position) {
        Optional<MyUser> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            MyUser user = optionalUser.get();

            Optional<MyUser> existingUser = userRepo.findByEmail(email);
            if (existingUser.isPresent()) {
                return "Данный email занят";
            }

            Optional<MyUser> existingUserViaPhone = userRepo.findByPhoneNumber(phoneNumber);
            if (existingUserViaPhone.isPresent()) {
                return "Данный номер телефона занят";
            }

            if (!email.matches(EMAIL_REGEX)) {
                return "Неверный формат email";
            }

            if (!phoneNumber.matches(PHONE_REGEX)) {
                return "Введите номер в формате 8хххххххххх";
            }

            user.setName(name);
            user.setSurname(surname);
            user.setMiddleName(middleName);
            user.setBirthdate(birthdate);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setPosition(position);
            userRepo.save(user);
            return "Изменено";
        } else {
            return "Пользователь не найден";
        }
    }


    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable("id") Integer id) {
        Optional<MyUser> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            userRepo.delete(optionalUser.get());
            return "Удалено";
        } else {
            return "Пользователь не найден";
        }
    }
}