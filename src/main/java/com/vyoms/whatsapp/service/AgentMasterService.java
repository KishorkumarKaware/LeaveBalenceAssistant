package com.vyoms.whatsapp.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vyoms.whatsapp.model.AgentMaster;
import com.vyoms.whatsapp.repo.AgentMasterRepository;

@Service
public class AgentMasterService {

	@Autowired
	AgentMasterRepository agentMasterRepository;

	public HashMap<String,AgentMaster> getListOfAgentMaster() {

		HashMap<String,AgentMaster> agents=new HashMap<>();
		List<AgentMaster> agentMasters=(List<AgentMaster>) agentMasterRepository.findAll();
		for(AgentMaster agent:agentMasters)
		{
			agents.put(agent.getTelephoneNumber(), agent);
		}
		return agents;

	}
}
