package com.vyoms.whatsapp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vyoms.whatsapp.model.AgentMaster;

@Repository
public interface AgentMasterRepository extends CrudRepository<AgentMaster, Integer> {

}
