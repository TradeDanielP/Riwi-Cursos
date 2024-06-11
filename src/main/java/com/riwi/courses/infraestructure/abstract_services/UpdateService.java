package com.riwi.courses.infraestructure.abstract_services;

public interface UpdateService<RQ, RS, ID> {
    public RS update(ID id, RQ request);
}
