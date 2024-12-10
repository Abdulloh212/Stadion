package uz.pdp.stadium.Repo;

import jakarta.persistence.EntityManager;
import uz.pdp.stadium.Data.MyListener;
import uz.pdp.stadium.Entity.Days;
import uz.pdp.stadium.Entity.Hours;

import java.util.List;

public class HoursRepo extends BaseRepo<Hours>{

    public List<Hours> findByDataId(int id) {
        try (EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Hours> hoursList = entityManager.createQuery("FROM Hours h WHERE h.day.id = :dayId", Hours.class)
                    .setParameter("dayId", id)
                    .getResultList();
            entityManager.getTransaction().commit();
            return hoursList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
