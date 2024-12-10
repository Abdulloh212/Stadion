package uz.pdp.stadium.Repo;

import jakarta.persistence.EntityManager;
import uz.pdp.stadium.Data.MyListener;
import uz.pdp.stadium.Entity.User;

public class UserRepo extends BaseRepo<User>{
    public User find(String email, String password) {
        EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        Object singleResult = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email and u.password = :password ")
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        entityManager.getTransaction().commit();
        if (singleResult != null) {
            return (User) singleResult;
        }
        return null;
    }
}
