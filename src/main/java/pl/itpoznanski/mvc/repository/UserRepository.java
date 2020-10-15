package pl.itpoznanski.mvc.repository;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.itpoznanski.mvc.builder.UserBuilder;
import pl.itpoznanski.mvc.entity.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
//implementacja repo
@Repository
public class UserRepository implements UserRepo<User>{
    private Map<String,User> stringUserMap = new HashMap<>();

    @Override
    public User save(User domain) {
        User result = stringUserMap.get(domain.getId());
        if(result != null){
            result.setName(domain.getName());
            result.setDescription(domain.getDescription());
            result.setAge(domain.getAge());
            domain = result;
        }
        stringUserMap.put(domain.getId(), domain);
        return stringUserMap.get(domain.getId());
    }

    @Override
    public void delete(User domain) {
        stringUserMap.remove(domain.getId());
    }

    @Override
    public User findById(String id) {
        return stringUserMap.get(id);
    }

    @Override
    public Iterable<User> findAll() {
       // User user = UserBuilder.create().withAge(10).withName("Piotr").withDescription("Fajny Men").build();
       // stringUserMap.put(user.getId(),user);
        return stringUserMap.entrySet().stream().
                map(Map.Entry::getValue).collect(Collectors.toList());
    }
}
