package com.example.course3.repository;

import com.example.course3.DTO.RecipientAndPrice;
import com.example.course3.entities.Delivery;
import com.example.course3.entities.Plant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class DeliveryRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }
    Delivery find(Long id){
        return entityManager.find(Delivery.class,id);
    }
    Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }
    void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class,id);
        entityManager.remove(delivery);
    }

    public List<Delivery> findDeliveriesByName(String name){
        TypedQuery<Delivery> query = entityManager.createNamedQuery("Delivery.findByName",Delivery.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public RecipientAndPrice getBill(Long deliveryId){
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery( RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);

        query.select(cb.construct(
                                RecipientAndPrice.class,
                                root.get("delivery").get("name"),
                                cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
