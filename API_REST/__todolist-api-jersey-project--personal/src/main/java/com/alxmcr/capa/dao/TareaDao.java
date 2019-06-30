package com.alxmcr.capa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alxmcr.capa.dao.bd.ConexionBD;
import com.alxmcr.capa.dao.bd.ConfiguracionBD;
import com.alxmcr.capa.dto.Tarea;
import com.alxmcr.constantes.ConstanteDatoConfigBD;
import com.alxmcr.constantes.ConstanteDriverDB;
import com.alxmcr.constantes.ConstanteEstructuraQueriesSQL;
import com.alxmcr.constantes.ConstanteGeneral;
import com.alxmcr.constantes.ConstanteMensajes;
import com.alxmcr.constantes.ConstanteNombreTabla;
import com.alxmcr.constantes.ConstanteTareaBD;
import com.alxmcr.constantes.ConstanteTipoDatoBD;
import com.alxmcr.constantes.ConstanteUrlJDBC;
import com.alxmcr.utiles.UtilDAO;
import com.alxmcr.utiles.UtilDTO;
import com.alxmcr.utiles.UtilREST;

public class TareaDao {

	private ConfiguracionBD configDB;

	public TareaDao(String usuarioBD, String passwordBD, String ipServidor,
			int puertoBD) {

		// TODO Cambiar el nombre de la base de datos
		String nombreBD = ConstanteDatoConfigBD.NAME_BD;

		// CADENA DE CONEXION
		String cadenaConexionBD = ConstanteUrlJDBC.JDBC_URL_MYSQL;
		// DRIVE
		String driverJDBC = ConstanteDriverDB.DRIVER_MYSQL;

		String[] datosUrlJDBC = { ipServidor, String.valueOf(puertoBD),
				nombreBD };

		configDB = new ConfiguracionBD();
		configDB.establecerDatosConexion(usuarioBD, passwordBD, puertoBD,
				cadenaConexionBD, driverJDBC, nombreBD, datosUrlJDBC);
	}

	public List<Tarea> findAll() throws SQLException {
		List<Tarea> salida = new ArrayList<Tarea>();

		ConexionBD cnxBD = configDB.obtenerConexionConectada();
		Connection conexion = cnxBD.getConexion();

		if (conexion != null) {
			// Datos de la tabla: USUARIO
			String nombreEsquemaDB = cnxBD.getNameBD();
			String nombreTabla = ConstanteNombreTabla.TABLA_TAREA;

			// Datos SELECT
			String[] datosSelect = { ConstanteGeneral.ASTERISCO,
					nombreEsquemaDB, nombreTabla };

			String querySelect = UtilDTO.cambioValores(
					ConstanteEstructuraQueriesSQL.SELECT, datosSelect);

			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				ps = conexion.prepareStatement(querySelect);
				rs = ps.executeQuery();

				while (rs.next()) {
					String id = rs.getString(1);
					String texto = rs.getString(2);
					Date fechaCreacion = rs.getDate(3);
					Time horaCreacion = rs.getTime(4);
					Date fechaModificacion = rs.getDate(5);
					Time horaModificacion = rs.getTime(6);
					String estado = rs.getString(7);

					Tarea tarea = new Tarea();
					tarea.setId(id);
					tarea.setTexto(texto);
					tarea.setFecha_creacion(fechaCreacion);
					tarea.setHora_creacion(horaCreacion);
					tarea.setFecha_modificacion(fechaModificacion);
					tarea.setHora_modificacion(horaModificacion);
					tarea.setEstado(estado);

					salida.add(tarea);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
					ps.close();
				}
				if (rs != null) {
					rs.close();
				}
				if (conexion != null) {
					System.out.println(ConstanteMensajes.CONEXION_CERRADA);
					conexion.close();
				}
			}
		}
		return salida;
	}

	public Tarea create(Tarea tarea) {

		Tarea salida = null;

		try {

			if (tarea != null) {

				ConexionBD cnxBD = configDB.obtenerConexionConectada();
				Connection conexion = cnxBD.getConexion();

				if (conexion != null) {
					// Datos de la tabla: USUARIO
					String nombreEsquemaDB = cnxBD.getNameBD();
					String nombreTabla = ConstanteNombreTabla.TABLA_TAREA;

					String[] tipoDatos = { ConstanteTipoDatoBD.TIPO_VARCHAR2,
							ConstanteTipoDatoBD.TIPO_VARCHAR2,
							ConstanteTipoDatoBD.TIPO_DATE_MYSQL,
							ConstanteTipoDatoBD.TIPO_TIME_MYSQL,
							ConstanteTipoDatoBD.TIPO_DATE_MYSQL,
							ConstanteTipoDatoBD.TIPO_TIME_MYSQL,
							ConstanteTipoDatoBD.TIPO_VARCHAR2 };

					String[] nombres = { ConstanteTareaBD.IDTAREA,
							ConstanteTareaBD.TEXTO,
							ConstanteTareaBD.FECHACREACION,
							ConstanteTareaBD.HORACREACION,
							ConstanteTareaBD.FECHAMODIFICACION,
							ConstanteTareaBD.HORAMODIFICACION,
							ConstanteTareaBD.ESTADO };

					// Generando el ID
					String idTarea = UtilREST.nextSessionId();
					String texto = tarea.getTexto();
					Date fechaCreacion = tarea.getFecha_creacion();
					Time horaCreacion = tarea.getHora_creacion();
					Date fechaModificacion = tarea.getFecha_modificacion();
					Time horaModificacion = tarea.getHora_modificacion();
					String estado = tarea.getEstado();

					// Convertir java.sql.Date to String
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					String fechaCreacionStr = df.format(fechaCreacion);
					String fechaModificacionStr = df.format(fechaModificacion);

					// Convertir java.sql.Time to String
					DateFormat dfs = new SimpleDateFormat("HH:mm:ss");
					String horaCreacionStr = dfs.format(horaCreacion);
					String horaModificacionStr = dfs.format(horaModificacion);

					String[] valores = { idTarea, texto, fechaCreacionStr,
							horaCreacionStr, fechaModificacionStr,
							horaModificacionStr, estado };

					String[] valoresConvertidos = UtilDTO.convertirDatosASQL(
							tipoDatos, valores);

					System.out.println("valores->" + Arrays.toString(valores));
					System.out.println("valoresConvertidos->"
							+ Arrays.toString(valoresConvertidos));

					String nombresStr = UtilDTO.construirArrayToStringSeparado(
							nombres, ConstanteGeneral.COMA);
					String valoresConvertidosStr = UtilDTO
							.construirArrayToStringSeparado(valoresConvertidos,
									ConstanteGeneral.COMA);

					String[] datosInsert = { nombreEsquemaDB, nombreTabla,
							nombresStr, valoresConvertidosStr };

					String queryDefault = ConstanteEstructuraQueriesSQL.INSERT;
					// Query final : INSERT
					String queryInsert = UtilDTO.cambioValores(queryDefault,
							datosInsert);

					System.out.println("queryInsert->" + queryInsert);

					PreparedStatement ps = null;
					ResultSet rs = null;

					try {
						conexion.setAutoCommit(false);
						ps = conexion.prepareStatement(queryInsert);
						int resultado = ps.executeUpdate();

						if (resultado == 1) {
							System.out.println(ConstanteMensajes.EXITO_ADICION);

							salida = new Tarea();
							salida.setId(idTarea);
							salida.setTexto(texto);
							salida.setFecha_creacion(fechaCreacion);
							salida.setHora_creacion(horaCreacion);
							salida.setFecha_modificacion(fechaModificacion);
							salida.setHora_modificacion(horaModificacion);
							salida.setEstado(estado);

							conexion.commit();
						}

					} catch (Exception e) {

						if (conexion != null) {
							try {
								conexion.rollback();
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}

					} finally {
						if (ps != null) {
							ps.close();
						}
						if (rs != null) {
							rs.close();
						}
						if (conexion != null) {
							System.out
									.println(ConstanteMensajes.CONEXION_CERRADA);
							conexion.setAutoCommit(true);
							conexion.close();
						}
					}
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salida;
	}

	public Tarea findById(String id) throws SQLException {

		Tarea salida = null;

		if (id != null) {
			ConexionBD cnxBD = configDB.obtenerConexionConectada();
			Connection conexion = cnxBD.getConexion();

			if (conexion != null) {
				// Datos de la tabla: USUARIO
				String nombreEsquemaDB = cnxBD.getNameBD();
				String nombreTabla = ConstanteNombreTabla.TABLA_TAREA;

				String[] tipoDatos = { ConstanteTipoDatoBD.TIPO_VARCHAR2 };

				String[] nombresWhere = { ConstanteTareaBD.IDTAREA };

				String[] valoresWhere = { id };

				String[] valoresWhereConvertidos = UtilDTO.convertirDatosASQL(
						tipoDatos, valoresWhere);

				// Construccion WHERE
				String whereSelect = UtilDTO.construirNombreYValorPorCaracter(
						nombresWhere, valoresWhereConvertidos,
						ConstanteGeneral.OPERADOR_AND);

				// Datos SELECT
				String[] datosSelect = { ConstanteGeneral.ASTERISCO,
						nombreEsquemaDB, nombreTabla, whereSelect };

				String querySelect = UtilDTO.cambioValores(
						ConstanteEstructuraQueriesSQL.SELECT_CON_WHERE,
						datosSelect);

				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					ps = conexion.prepareStatement(querySelect);
					rs = ps.executeQuery();

					if (rs.next()) {
						String idTarea = rs.getString(1);
						String texto = rs.getString(2);
						Date fechaCreacion = rs.getDate(3);
						Time horaCreacion = rs.getTime(4);
						Date fechaModificacion = rs.getDate(5);
						Time horaModificacion = rs.getTime(6);
						String estado = rs.getString(7);

						salida = new Tarea();
						salida.setId(idTarea);
						salida.setTexto(texto);
						salida.setFecha_creacion(fechaCreacion);
						salida.setHora_creacion(horaCreacion);
						salida.setFecha_modificacion(fechaModificacion);
						salida.setHora_modificacion(horaModificacion);
						salida.setEstado(estado);
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (ps != null) {
						ps.close();
					}
					if (rs != null) {
						rs.close();
					}
					if (conexion != null) {
						System.out.println(ConstanteMensajes.CONEXION_CERRADA);
						conexion.close();
					}
				}
			}
		}

		return salida;

	}

	public boolean edit(Tarea nueva, Tarea anterior) {

		try {

			if (nueva != null && anterior != null) {

				System.out.println("nueva->" + nueva);
				System.out.println("anterior->" + anterior);

				String idTarea = nueva.getId();

				Tarea isExiste = this.findById(idTarea);

				if (isExiste != null) {

					ArrayList<String> valDistintos = UtilDTO
							.diferenciaPorValores(nueva, anterior);

					ArrayList<String> tiposDatosDistintos = UtilDTO
							.diferenciaPorTipoDatos(nueva, anterior);

					ArrayList<String> nombresColumnas = UtilDTO
							.diferenciaPorColumnas(nueva, anterior);

					if (valDistintos != null && tiposDatosDistintos != null
							&& nombresColumnas != null) {

						int nroTipos = tiposDatosDistintos.size();
						String[] tipoDatos = new String[nroTipos];

						for (int i = 0; i < nroTipos; i++) {
							tipoDatos[i] = tiposDatosDistintos.get(i);
						}

						int nroValores = valDistintos.size();
						String[] datos = new String[nroValores];

						for (int i = 0; i < nroTipos; i++) {
							datos[i] = valDistintos.get(i);
						}

						String[] datosConvertidos = UtilDTO.convertirDatosASQL(
								tipoDatos, datos);

						String setStr = UtilDAO.construirSetSQLs(
								nombresColumnas, datosConvertidos);

						StringBuffer where = new StringBuffer();
						where.append(ConstanteTareaBD.IDTAREA);
						where.append(" = ");
						where.append("'").append(idTarea).append("'");

						String whereStr = where.toString();

						if (!setStr.isEmpty() && !whereStr.isEmpty()) {
							ConexionBD cnxBD = configDB
									.obtenerConexionConectada();
							Connection conexion = cnxBD.getConexion();

							if (conexion != null) {
								// Datos de la tabla: USUARIO
								String nombreEsquemaDB = cnxBD.getNameBD();
								String nombreTabla = ConstanteNombreTabla.TABLA_TAREA;

								String[] datosUpdate = { nombreEsquemaDB,
										nombreTabla, setStr, whereStr };

								// SQL UPDATE
								String queryUpdate = UtilDTO
										.cambioValores(
												ConstanteEstructuraQueriesSQL.UPDATE_CON_WHERE,
												datosUpdate);

								System.out.println("queryUpdate->"
										+ queryUpdate);

								PreparedStatement ps = null;
								ResultSet rs = null;

								try {
									conexion.setAutoCommit(false);
									ps = conexion.prepareStatement(queryUpdate);
									int resultado = ps.executeUpdate();

									if (resultado == 1) {
										conexion.commit();
										System.out
												.println(ConstanteMensajes.EXITO_ACTUALIZACION);
										return true;
									}

								} catch (Exception e) {
									if (conexion != null) {
										try {
											conexion.rollback();
										} catch (Exception e2) {
											e2.printStackTrace();
										}
									}
								} finally {
									if (ps != null) {
										ps.close();
									}
									if (rs != null) {
										rs.close();
									}
									if (conexion != null) {
										System.out
												.println(ConstanteMensajes.CONEXION_CERRADA);
										conexion.setAutoCommit(true);
										conexion.close();
									}
								}
							}
						} else {
							System.out
									.println(ConstanteMensajes.PROBLEMAS_CON_QUERY);
						}

					} else {
						System.out
								.println(ConstanteMensajes.NO_HAY_NADA_QUE_ACTUALIZAR);
					}

				} else {
					System.out.println(ConstanteMensajes.NO_EXISTE_REGISTRO);
				}

			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
