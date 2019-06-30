package com.alxmcr.capa.dao.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import com.alxmcr.constantes.ConstanteMensajes;
import com.alxmcr.utiles.UtilDTO;

public class ConexionBD {
	private Connection conexion;

	/** Datos de la BD */
	private String usuarioBD;
	private String passwordBD;
	private String nameBD;
	private int puertoBD;

	/** Gestor de la BD */
	private String cadenaConexionBD;
	private String driver;

	/** Datos URL JDBC */
	private String[] datosUrlJDBC;

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public String getUsuarioBD() {
		return usuarioBD;
	}

	public void setUsuarioBD(String usuarioBD) {
		this.usuarioBD = usuarioBD;
	}

	public String getPasswordBD() {
		return passwordBD;
	}

	public void setPasswordBD(String passwordBD) {
		this.passwordBD = passwordBD;
	}

	public String getNameBD() {
		return nameBD;
	}

	public void setNameBD(String nameBD) {
		this.nameBD = nameBD;
	}

	public int getPuertoBD() {
		return puertoBD;
	}

	public void setPuertoBD(int puertoBD) {
		this.puertoBD = puertoBD;
	}

	public String getCadenaConexionBD() {
		return cadenaConexionBD;
	}

	public void setCadenaConexionBD(String cadenaConexionBD) {
		this.cadenaConexionBD = cadenaConexionBD;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String[] getDatosUrlJDBC() {
		return datosUrlJDBC;
	}

	public void setDatosUrlJDBC(String[] datosUrlJDBC) {
		this.datosUrlJDBC = datosUrlJDBC;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConexionBD [");
		if (conexion != null) {
			builder.append("conexion=");
			builder.append(conexion);
			builder.append(", ");
		}
		if (usuarioBD != null) {
			builder.append("usuarioBD=");
			builder.append(usuarioBD);
			builder.append(", ");
		}
		if (passwordBD != null) {
			builder.append("passwordBD=");
			builder.append(passwordBD);
			builder.append(", ");
		}
		if (nameBD != null) {
			builder.append("nameBD=");
			builder.append(nameBD);
			builder.append(", ");
		}
		builder.append("puertoBD=");
		builder.append(puertoBD);
		builder.append(", ");
		if (cadenaConexionBD != null) {
			builder.append("cadenaConexionBD=");
			builder.append(cadenaConexionBD);
			builder.append(", ");
		}
		if (driver != null) {
			builder.append("driver=");
			builder.append(driver);
			builder.append(", ");
		}
		if (datosUrlJDBC != null) {
			builder.append("datosUrlJDBC=");
			builder.append(Arrays.toString(datosUrlJDBC));
		}
		builder.append("]");
		return builder.toString();
	}

	public boolean isTodosLosCamposInformados() {
		boolean isNulos = this.usuarioBD == null || this.passwordBD == null
				|| this.nameBD == null || this.datosUrlJDBC == null
				|| this.driver == null;
		boolean isArregloVacio = this.datosUrlJDBC == null ? true
				: this.datosUrlJDBC.length <= 0;

		if (!isNulos) {
			if (!isArregloVacio) {
				return true;
			} else {
				System.out.println(ConstanteMensajes.DATO_VACIO);
			}
		}

		return false;
	}

	public void abrirConexion() {
		if (this.isTodosLosCamposInformados()) {

			try {

				Class.forName(this.driver);

				String url = UtilDTO.cambioValores(this.cadenaConexionBD,
						this.datosUrlJDBC);

				this.setCadenaConexionBD(url);

				conexion = DriverManager.getConnection(url, this.usuarioBD,
						this.passwordBD);

				System.out.println(ConstanteMensajes.CONEXION_ABIERTA);

			} catch (ClassNotFoundException e) {
				System.out.println(ConstanteMensajes.ERROR_CLASE_NO_ENCONTRADA);
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println(ConstanteMensajes.ERROR_APERTURA_CONEXION);
				e.printStackTrace();
			}

		} else {
			System.out.println(ConstanteMensajes.DATOS_INCOMPLETOS);
			System.out.println(ConstanteMensajes.ERROR_APERTURA_CONEXION);
		}
	}

	public void cerrarConexion() {
		try {
			if (this.conexion != null && !this.conexion.isClosed()) {
				System.out.println(ConstanteMensajes.CONEXION_CERRADA);
				this.conexion.close();
				System.out.println(ConstanteMensajes.CONEXION_CERRADA);
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
				System.out.println(ConstanteMensajes.ERROR_CONEXION_NO_ABIERTA);
			}
		} catch (SQLException e) {
			System.out.println(ConstanteMensajes.ERROR_CERRAR_CONEXION);
			e.printStackTrace();
		}
	}
}
