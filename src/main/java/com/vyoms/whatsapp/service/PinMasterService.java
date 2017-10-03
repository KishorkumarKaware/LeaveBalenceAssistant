package com.vyoms.whatsapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vyoms.whatsapp.model.PinMaster;
import com.vyoms.whatsapp.repo.PinMasterRepository;

@Service
public class PinMasterService {

	@Autowired
	PinMasterRepository pinMasterRepository;

	public HashMap<String,PinMaster> getListOfPinMaster() {

		HashMap<String,PinMaster> pinCodes=new HashMap<>();
		List<PinMaster> pinMasters=(List<PinMaster>) pinMasterRepository.findAll();
		for(PinMaster pinMaster:pinMasters)
		{
			pinCodes.put(pinMaster.getCorrectedpincode(), pinMaster);
		}
		return pinCodes;

	}
}
