package pl.itpoznanski.mvc.entity;

import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
public class User {

    @NonNull
    private String id;

    private String name;

    private int age;

    private String description;

    public User() {
        this.id=UUID.randomUUID().toString();
    }
    public User(String id, String name, int age){
        this();
        this.id=id;
        this.name=name;
        this.age=age;
    }
    public User(String id, String name, int age,String description){
        this();
        this.id = id;
        this.name=name;
        this.age=age;
        this.description=description;
    }
}
