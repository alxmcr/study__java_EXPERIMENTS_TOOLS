package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dao.MeaningDao;
import com.dto.Meaning;

public class ServiceMeaningImpl implements ServiceMeaning {

	@Autowired
	@Qualifier("daoMeaning")
	private MeaningDao daoMeaning;

	public Meaning create(Meaning nuevaMeaning) throws Exception {
		return daoMeaning.create(nuevaMeaning);
	}

	public Meaning update(Meaning MeaningModificado) throws Exception {
		return daoMeaning.update(MeaningModificado);
	}

	public Meaning delete(String idMeaning) throws Exception {
		return daoMeaning.delete(idMeaning);
	}

	public Meaning findById(String idMeaning) throws Exception {
		return daoMeaning.findById(idMeaning);
	}

	public List<Meaning> findContainsTexto(String texto) throws Exception {
		return daoMeaning.findContainsTexto(texto);
	}

	public List<Meaning> listAllMeaningsByEstado(String estado)
			throws Exception {
		return daoMeaning.listAllMeaningsByEstado(estado);
	}

}
