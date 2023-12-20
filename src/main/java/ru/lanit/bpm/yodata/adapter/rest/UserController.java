package ru.lanit.bpm.yodata.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.lanit.bpm.yodata.adapter.rest.dto.SetRoleDTO;
import ru.lanit.bpm.yodata.adapter.rest.dto.UserControllerDTO;
import ru.lanit.bpm.yodata.app.api.DuplicateEntityException;
import ru.lanit.bpm.yodata.app.api.NotFoundException;
import ru.lanit.bpm.yodata.app.api.UserRoleService;
import ru.lanit.bpm.yodata.app.api.UserService;
import ru.lanit.bpm.yodata.domain.Subscription;
import ru.lanit.bpm.yodata.domain.User;
import ru.lanit.bpm.yodata.domain.UserRole;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/yodata/admin/users")
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;


    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/roles")
    public List<UserRole> getAllUsersWithRoles(){
        return userService.getAllUsersWithRoles();
    }
    @PutMapping("/roles")
    public ResponseEntity<String> setRole(@RequestBody SetRoleDTO setRoleDTO){
        try {
            userRoleService.setRole(setRoleDTO.getLogin(), setRoleDTO.getRole());
            return ResponseEntity.ok("Ok");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/roles")
    public ResponseEntity<String> deleteUserRoles(@RequestBody Long id){
        try {
            userRoleService.deleteRole(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
        try {
            userService.registerUser(user.getLogin(), user.getPassword(),
                user.getFirstName(), user.getLastName(), user.getTelegramId());
            return ResponseEntity.ok("Created");
        } catch (DuplicateEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{login}/getSubscriptions")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable String login){
        try {
            return ResponseEntity.ok().body(userService.getUserSubscriptions(login));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{login}")
    public ResponseEntity<User> getUser(@PathVariable String login){
        try {
            return ResponseEntity.ok().body(userService.findUserByLogin(login).get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UserControllerDTO userControllerDTO){
        try {
            userService.changePassword(userControllerDTO.getLogin(), userControllerDTO.getPassword());
            return ResponseEntity.ok().body("Done");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/checkPassword")
    public ResponseEntity<String> checkPassword(@RequestBody UserControllerDTO userControllerDTO){
        try {
            if (userService.checkPassword(userControllerDTO.getLogin(), userControllerDTO.getPassword())){
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{login}")
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable String login){
        try {
            userService.deleteUser(login);
            return  ResponseEntity.ok("Deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
