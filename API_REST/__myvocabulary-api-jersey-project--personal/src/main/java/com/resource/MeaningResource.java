package com.resource;

public interface MeaningResource {

	String create(String jsonMeaning) throws Exception;

	String delete(String idMeaning) throws Exception;

	String update(String id, String jsonMeaningModificada) throws Exception;

	String findById(String idMeaning) throws Exception;

	String listAllMeaningsActivas() throws Exception;

}
