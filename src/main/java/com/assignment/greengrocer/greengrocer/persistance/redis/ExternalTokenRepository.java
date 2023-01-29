package com.assignment.greengrocer.greengrocer.persistance.redis;

import org.springframework.data.repository.CrudRepository;

public interface ExternalTokenRepository extends CrudRepository<ExternalToken, String> {

}
