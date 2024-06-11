package com.riwi.courses.infraestructure.abstract_services;

import org.springframework.data.domain.Page;

public interface CrudService<RQ, RS, ID> {
    
    public RS create(RQ request);

    public RS getById(ID id);

    public void delete(ID id);

    public Page<RS> getAll(int page, int size);

}
