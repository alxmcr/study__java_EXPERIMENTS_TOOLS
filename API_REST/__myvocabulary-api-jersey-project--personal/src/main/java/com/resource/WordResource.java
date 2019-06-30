package com.resource;

public interface WordResource {

	String create(String jsonWord) throws Exception;

	String delete(String idWord) throws Exception;

	String update(String id, String jsonWordModificada) throws Exception;

	String findById(String idWord) throws Exception;
	
	String findContainsTexto(String texto) throws Exception;

	String listAllWordsActivas() throws Exception;

}
