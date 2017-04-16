package com.cgi.dentistapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class DentistVisitDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(DentistVisitEntity visit) {
        entityManager.persist(visit);
    }

    @ModelAttribute("messages")
    public List<DentistVisitEntity> getAllVisits() {
        return entityManager.createQuery("SELECT e FROM DentistVisitEntity e").getResultList();
    }

    public DentistVisitEntity viewVisit(Long id){
        String selectQuery = "SELECT e FROM DentistVisitEntity e WHERE id=" + id;
        DentistVisitEntity singleResult = (DentistVisitEntity) entityManager.createQuery(selectQuery).getSingleResult();
        return  singleResult;
    }

    public void updateVisit(DentistVisitEntity visit) {
        Long visitId= visit.getId();
        String dentistName= visit.getDentistName();
        String visitTime = visit.getVisitTime();

        String updateQuery = "UPDATE DentistVisitEntity " +
                "SET dentistName='" + dentistName+"', visitTime='"+ visitTime
                +"' WHERE id=" + visitId;
        entityManager.createQuery(updateQuery).executeUpdate();
    }

    public void deleteVisit(int id) {
        String selectQuery = "SELECT e FROM DentistVisitEntity e WHERE id=" + id;
        List<DentistVisitEntity> allVisitsToRemove = entityManager.createQuery(selectQuery).getResultList();
        for (DentistVisitEntity m: allVisitsToRemove) {
            entityManager.remove(m);
        }
    }

    public List<DentistVisitEntity> searchVisitByDentistName(String dentistName) {
        String selectQuery = "SELECT e FROM DentistVisitEntity e WHERE dentistName='" + dentistName +"'";
         return entityManager.createQuery(selectQuery).getResultList();
    }
    public List<DentistVisitEntity> getDentistList() {
        String selectQuery = "SELECT DISTINCT dentistName FROM DentistVisitEntity ORDER BY dentistName";
        List<DentistVisitEntity> list = entityManager.createQuery(selectQuery).getResultList();
        return entityManager.createQuery(selectQuery).getResultList();
    }
}
