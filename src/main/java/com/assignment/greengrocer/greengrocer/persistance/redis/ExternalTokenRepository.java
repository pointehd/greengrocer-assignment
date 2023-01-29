package com.assignment.greengrocer.greengrocer.persistance.redis;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import org.springframework.data.repository.CrudRepository;

public interface ExternalTokenRepository extends CrudRepository<ExternalToken, GreengrocerType> {

}
