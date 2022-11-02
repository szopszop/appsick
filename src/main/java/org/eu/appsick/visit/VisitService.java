package org.eu.appsick.visit;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VisitService {

    private final VisitDao visitDao;

    public VisitService(VisitDao visitDao){
        this.visitDao = visitDao;
    }

    public Visit getById(String id){
        UUID uuid = UUID.fromString(id);
        Visit visit = visitDao.getVisit(uuid);
        return visit;
    }

}
