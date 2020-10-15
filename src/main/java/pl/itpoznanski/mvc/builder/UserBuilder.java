package pl.itpoznanski.mvc.builder;

import pl.itpoznanski.mvc.entity.User;
//builder klasy User, wzorzec kreacyjny
public class UserBuilder {
    //instancja statyczna klasy UserBuilder
    private static UserBuilder instance =new UserBuilder();
    private String id = null;
    private String description="";
    private String name="";
    private int age = 0;

    private UserBuilder(){

    }
    //zwrocenie instancji klasy UserBuilder
    public static UserBuilder create(){
        return instance;
    }
    public UserBuilder withDescription(String description){
        this.description=description;
        return instance;
    }
    public UserBuilder witdId(String id){
        this.id=id;
        return instance;
    }
    public UserBuilder withName(String name){
        this.name=name;
        return instance;
    }
    public UserBuilder withAge(int age){
        this.age=age;
        return instance;
    }
    //stworzenie klasy User przez buildera
    public User build(){
        User result = new User(this.id, this.description, this.age,this.name);
        if(id != null)
            result.setId(id);
        return result;
    }
}
