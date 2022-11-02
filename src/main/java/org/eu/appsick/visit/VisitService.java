package org.eu.appsick.visit;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VisitService {

    // TODO: dependency injection
    private final VisitDao visitDao = new VisitDaoOrm();

    public Visit getById(String id){
        UUID uuid = UUID.fromString(id);
        Visit visit = visitDao.getVisit(uuid);
        return visit;
    }

}
