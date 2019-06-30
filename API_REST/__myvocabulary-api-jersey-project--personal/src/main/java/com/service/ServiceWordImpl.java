package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dao.WordDao;
import com.dto.Word;

public class ServiceWordImpl implements ServiceWord {

	@Autowired
	@Qualifier("daoWord")
	private WordDao daoWord;

	public List<Word> listAllWordsByEstado(String estado) throws Exception {
		return daoWord.listAllWordsByEstado(estado);
	}

	public Word create(Word Word) throws Exception {
		return daoWord.create(Word);
	}

	public Word findById(String idWord) throws Exception {
		return daoWord.findById(idWord);
	}

	public Word update(Word WordModificada) throws Exception {
		return daoWord.update(WordModificada);
	}

	public Word delete(String idWord) throws Exception {
		return daoWord.delete(idWord);
	}

	public List<Word> findContainsTexto(String texto) throws Exception {
		return daoWord.findContainsTexto(texto);
	}

}
