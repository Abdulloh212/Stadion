package uz.pdp.stadium.Repo;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import uz.pdp.stadium.Data.MyListener;
import uz.pdp.stadium.Entity.Stadium;
import uz.pdp.stadium.Entity.abs.BaseEntity;

import java.util.List;

public class StadiumRepo extends BaseRepo<Stadium> {

    public List<Stadium> findByRegionId(int regionId) {
        try (EntityManager entityManager = MyListener.getEntityManagerFactory().createEntityManager()) {
            List<Stadium>stadiums = entityManager.createQuery("FROM Stadium WHERE region.id = :regionId", Stadium.class).setParameter("regionId", regionId).getResultList();
            return stadiums;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
