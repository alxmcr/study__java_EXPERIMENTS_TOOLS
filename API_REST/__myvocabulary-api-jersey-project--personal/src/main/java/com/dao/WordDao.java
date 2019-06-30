package com.dao;

import java.util.List;

import com.dto.Word;

public interface WordDao {

	Word create(Word nuevaWord) throws Exception;

	Word update(Word WordModificada) throws Exception;

	Word delete(String idWord) throws Exception;

	Word findById(String idWord) throws Exception;

	List<Word> findContainsTexto(String texto) throws Exception;

	List<Word> listAllWordsByEstado(String estado) throws Exception;

}
