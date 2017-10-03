package com.vyoms.whatsapp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vyoms.whatsapp.model.PinMaster;

@Repository
public interface PinMasterRepository extends CrudRepository<PinMaster, Integer> {

}
