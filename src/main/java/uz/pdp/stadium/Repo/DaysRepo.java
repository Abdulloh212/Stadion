package uz.pdp.stadium.Repo;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import uz.pdp.stadium.Data.MyListener;
import uz.pdp.stadium.Entity.Days;

public class DaysRepo extends BaseRepo<Days> {
    public Days findByStadiumId(int id) {
        try (EntityManager entityManager=MyListener.getEntityManagerFactory().createEntityManager()){
            entityManager.getTransaction().begin();
            Days singleResult = entityManager.createQuery("FROM Days d WHERE d.stadium.id = :stadiumId", Days.class)
                    .setParameter("stadiumId",id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
            return singleResult;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
