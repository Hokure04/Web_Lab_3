package web.org.web_project_3;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;
import java.util.List;

@Stateless
public class DataRepository implements Serializable {
    @PersistenceContext
    private EntityManager db;

    public Dot create(Dot dot) {
        Dot newDot = new Dot(dot);
        db.persist(newDot);
        return newDot;
    }

    public List<Dot> findAll() {
        return db.createQuery("from Dot", Dot.class).getResultList();
    }
}
