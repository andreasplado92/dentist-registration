package com.cgi.dentistapp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.dentistapp.dao.DentistVisitDao;
import com.cgi.dentistapp.dao.entity.DentistVisitEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private DentistVisitDao dentistVisitDao;

    public void addVisit(String dentistName, String visitTime) {
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime);
        dentistVisitDao.create(visit);
    }

    public DentistVisitEntity viewVisit(Long id){
        return dentistVisitDao.viewVisit(id);
    }

    public List<DentistVisitEntity> listVisits () {
        return dentistVisitDao.getAllVisits();
    }

    public void updateVisit(Long id, String dentistName, String visitTime){
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime);
        visit.setId(id);
        dentistVisitDao.updateVisit(visit);
    }


    public void deleteVisit(int id){
        dentistVisitDao.deleteVisit(id);
    }

    public List<DentistVisitEntity> searchVisitByDentistName (String dentistName) {
        return dentistVisitDao.searchVisitByDentistName(dentistName);
    }

    public List<DentistVisitEntity> getDentistList () {
        return dentistVisitDao.getDentistList();
    }

}
