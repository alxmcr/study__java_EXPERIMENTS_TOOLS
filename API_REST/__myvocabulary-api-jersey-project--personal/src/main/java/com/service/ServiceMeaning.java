package com.service;

import java.util.List;

import com.dto.Meaning;

public interface ServiceMeaning {

	Meaning create(Meaning nuevaMeaning) throws Exception;

	Meaning update(Meaning MeaningModificado) throws Exception;

	Meaning delete(String idMeaning) throws Exception;

	Meaning findById(String idMeaning) throws Exception;

	List<Meaning> findContainsTexto(String texto) throws Exception;

	List<Meaning> listAllMeaningsByEstado(String estado) throws Exception;
}
