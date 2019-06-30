package com.alxmcr.capa.dao.bd;

import com.alxmcr.constantes.ConstanteMensajes;

public class ConfiguracionBD {
	private ConexionBD conexion;

	public void establecerDatosConexion(String usuarioBD, String passwordBD, int puertoBD, String cadenaConexionBD,
			String driverJDBC, String nombreBD, String[] datosUrlJDBC) {

		this.conexion = new ConexionBD();

		this.conexion.setUsuarioBD(usuarioBD);
		this.conexion.setPasswordBD(passwordBD);
		this.conexion.setPuertoBD(puertoBD);
		this.conexion.setCadenaConexionBD(cadenaConexionBD);
		this.conexion.setDriver(driverJDBC);
		this.conexion.setNameBD(nombreBD);

		this.conexion.setDatosUrlJDBC(datosUrlJDBC);

	}

	public ConexionBD obtenerConexionConectada() {
		if (this.conexion != null) {
			this.conexion.abrirConexion();
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}
		return this.conexion;
	}
}
