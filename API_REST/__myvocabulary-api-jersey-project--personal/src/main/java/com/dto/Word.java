package com.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the word database table.
 * 
 */
@Entity
@Table(name = "word")
@NamedQueries({
		@NamedQuery(name = "Word.findAll", query = "SELECT w FROM Word w"),
		@NamedQuery(name = "Word.findById", query = "SELECT w FROM Word w where w.id = :idPalabra"),
		@NamedQuery(name = "Word.findByTexto", query = "SELECT w FROM Word w where w.text = :texto"),
		@NamedQuery(name = "Word.findContainsTexto", query = "SELECT w FROM Word w where TRIM(lower(w.text)) LIKE :texto") })
public class Word implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false, length = 50)
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date creationDate;

	@Column(nullable = false)
	private Time creationTime;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date modificationDate;

	@Column(nullable = false)
	private Time modificationTime;

	@Column(nullable = false, length = 50)
	private String status;

	@Column(nullable = false, length = 50)
	private String text;

	// bi-directional many-to-one association to Meaning
	@OneToMany(mappedBy = "word", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Meaning> meanings;

	public Word() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Time getCreationTime() {
		return this.creationTime;
	}

	public void setCreationTime(Time creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationDate() {
		return this.modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Time getModificationTime() {
		return this.modificationTime;
	}

	public void setModificationTime(Time modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Meaning> getMeanings() {
		return this.meanings;
	}

	public void setMeanings(List<Meaning> meanings) {
		this.meanings = meanings;
	}

	public Meaning addMeaning(Meaning meaning) {
		getMeanings().add(meaning);
		meaning.setWord(this);

		return meaning;
	}

	public Meaning removeMeaning(Meaning meaning) {
		getMeanings().remove(meaning);
		meaning.setWord(null);

		return meaning;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word [id=");
		builder.append(id);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", creationTime=");
		builder.append(creationTime);
		builder.append(", modificationDate=");
		builder.append(modificationDate);
		builder.append(", modificationTime=");
		builder.append(modificationTime);
		builder.append(", status=");
		builder.append(status);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}

}