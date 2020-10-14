package pl.itpoznanski.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.itpoznanski.mvc.builder.UserBuilder;
import pl.itpoznanski.mvc.entity.User;
import pl.itpoznanski.mvc.error.BuilderUserValidationError;
import pl.itpoznanski.mvc.error.UserValidationError;
import pl.itpoznanski.mvc.repository.UserRepo;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserRepo<User> repository;

    @Autowired
    public UserController(UserRepo<User> repository) {
        this.repository = repository;
    }
    @GetMapping("/hello")
    public ResponseEntity<String>sayHello(){
        return new ResponseEntity<>("Hello User!", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Iterable<User>> getUsers(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User>getUserById(@PathVariable String id){
        return ResponseEntity.ok(repository.findById(id));
    }
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user,
                                        Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                       body(BuilderUserValidationError.fromBindingErrors(errors));
        }
        User result = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PatchMapping("/user/{id}")
    public ResponseEntity<User> udpateUserById(@RequestBody User user, @PathVariable String id){
        User result = repository.findById(id);
        result.setName(user.getName());
        repository.save(result);
        return ResponseEntity.ok(repository.findById(id));
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<User>udpadetPutUserById(@RequestBody User user, @PathVariable String id){
        User result = repository.findById(id);
        result.setName(user.getName());
        repository.save(result);
        return ResponseEntity.ok(repository.findById(id));
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable String id){
        repository.delete(UserBuilder.create().witdId(id).build());
        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public UserValidationError handleException(Exception exception) {
        return new UserValidationError(exception.getMessage());
    }
}
