package com.alxmcr.capa.dto;

import java.sql.Date;
import java.sql.Time;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tarea {
	private String id;
	private Date fecha_creacion;
	private Time hora_creacion;
	private Date fecha_modificacion;
	private Time hora_modificacion;
	private String texto;
	private String estado;

	public Tarea() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Time getHora_creacion() {
		return hora_creacion;
	}

	public void setHora_creacion(Time hora_creacion) {
		this.hora_creacion = hora_creacion;
	}

	public Date getFecha_modificacion() {
		return fecha_modificacion;
	}

	public void setFecha_modificacion(Date fecha_modificacion) {
		this.fecha_modificacion = fecha_modificacion;
	}

	public Time getHora_modificacion() {
		return hora_modificacion;
	}

	public void setHora_modificacion(Time hora_modificacion) {
		this.hora_modificacion = hora_modificacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tarea [id=");
		builder.append(id);
		builder.append(", fecha_creacion=");
		builder.append(fecha_creacion);
		builder.append(", hora_creacion=");
		builder.append(hora_creacion);
		builder.append(", fecha_modificacion=");
		builder.append(fecha_modificacion);
		builder.append(", hora_modificacion=");
		builder.append(hora_modificacion);
		builder.append(", texto=");
		builder.append(texto);
		builder.append(", estado=");
		builder.append(estado);
		builder.append("]");
		return builder.toString();
	}

}
