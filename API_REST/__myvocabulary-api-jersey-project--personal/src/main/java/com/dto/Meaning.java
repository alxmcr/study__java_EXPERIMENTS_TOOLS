package com.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the meaning database table.
 * 
 */
@Entity
@Table(name = "meaning")
@NamedQueries({
		@NamedQuery(name = "Meaning.findAll", query = "SELECT m FROM Meaning m"),
		@NamedQuery(name = "Meaning.findById", query = "SELECT m FROM Meaning m where m.id = :idPalabra"),
		@NamedQuery(name = "Meaning.findByTexto", query = "SELECT m FROM Meaning m where m.text = :texto"),
		@NamedQuery(name = "Meaning.findContainsTexto", query = "SELECT m FROM Meaning m where m.text LIKE '%:texto%'") })
public class Meaning implements Serializable {
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

	@Column(nullable = false, length = 45)
	private String status;

	@Column(nullable = false, length = 50)
	private String text;

	// bi-directional many-to-one association to Word
	@ManyToOne
	@JoinColumn(name = "idWord", nullable = false)
	@JsonBackReference
	private Word word;

	public Meaning() {
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

	public Word getWord() {
		return this.word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Meaning [id=");
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