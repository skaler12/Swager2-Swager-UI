package pl.itpoznanski.mvc.repository;

import java.util.Collection;
//repo z typami generycznymi
public interface UserRepo <T> {
    public T save(T domain);
    //public Iterable<T> save(Collection<T> domains);
    public void delete(T domain);
    public T findById(String id);
    public Iterable<T> findAll();
}
