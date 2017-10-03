package com.vyoms.whatsapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vyoms.whatsapp.model.EmpMaster;
import com.vyoms.whatsapp.repo.EmployeeMasterRepository;

@Service
public class EmpMasterService {

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	public HashMap<String,EmpMaster> getListOfEmpMaster() {

		HashMap<String,EmpMaster> employees=new HashMap<>();
		List<EmpMaster> employeeMasters=(List<EmpMaster>) employeeMasterRepository.findAll();
		for(EmpMaster employee:employeeMasters)
		{
			employees.put(employee.getTelephone(), employee);
		}
		return employees;

	}
}
