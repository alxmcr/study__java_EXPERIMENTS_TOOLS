package com.alxmcr.utiles;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alxmcr.capa.dto.Tarea;
import com.alxmcr.constantes.ConstanteEstructuraQueriesSQL;
import com.alxmcr.constantes.ConstanteFormatoFechaYHora;
import com.alxmcr.constantes.ConstanteGeneral;
import com.alxmcr.constantes.ConstanteMensajes;
import com.alxmcr.constantes.ConstanteTareaBD;
import com.alxmcr.constantes.ConstanteTipoDatoBD;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class UtilDTO {

	public static String cambiarFormatoCalendarBD(Calendar fecha,
			String formatoDateBD) {

		String salida = null;

		if (fecha != null) {
			if (formatoDateBD != null
					&& !ConstanteGeneral.CADENA_VACIA.equals(formatoDateBD)) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat(formatoDateBD);
					Date fechaRealizacionTareaDate = fecha.getTime();
					System.out.println("fechaRealizacionTareaDate:"
							+ fechaRealizacionTareaDate);
					salida = sdf.format(fechaRealizacionTareaDate);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}

		return salida;
	}

	public static String cambioValores(String cadena, String[] datos) {

		String resultado = ConstanteGeneral.CADENA_VACIA;

		if (datos != null && datos.length > 0) {
			if (cadena != null && !ConstanteGeneral.CADENA_VACIA.equals(cadena)) {
				try {
					MessageFormat mf = new MessageFormat(cadena);
					resultado = mf.format(datos);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}

		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return resultado;
	}

	public static String convertirArrayToStringSeparadoCaracter(
			String[] columnas, String caracterSeparacion) {

		String nombresColumnas = ConstanteGeneral.CADENA_VACIA;

		if (columnas != null && columnas.length > 0) {
			if (caracterSeparacion != null
					&& !ConstanteGeneral.CADENA_VACIA
							.equals(caracterSeparacion)) {
				for (int i = 0; i < columnas.length; i++) {
					nombresColumnas += columnas[i];
					if (i != columnas.length - 1) {
						nombresColumnas += caracterSeparacion;
					}
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return nombresColumnas;
	}

	public static String[] convertirDatosASQL(String[] tipoDatos, String[] datos) {
		String[] datosConvertidos = null;

		if (datos != null && datos.length > 0 && tipoDatos != null
				&& tipoDatos.length > 0 && datos.length == tipoDatos.length) {

			datosConvertidos = new String[datos.length];

			for (int i = 0; i < tipoDatos.length; i++) {
				if (ConstanteTipoDatoBD.TIPO_VARCHAR2.equals(tipoDatos[i])) {
					datosConvertidos[i] = "'" + datos[i] + "'";
				} else if (ConstanteTipoDatoBD.TIPO_DATE_ORACLE
						.equals(tipoDatos[i])) {

					Calendar fechaC = UtilDTO
							.convertirStringToCalendar(datos[i]);

					String fecha = UtilDTO.obtenerFechaOfCalendar(fechaC);
					String tiempo = UtilDTO.obtenerTiempoOfCalendar(fechaC);

					String fechaYTiempo = "'" + fecha
							+ ConstanteGeneral.ESPACIO_EN_BLANCO + tiempo + "'";

					String formatoFechaYTiempo = ConstanteFormatoFechaYHora.FORMATO_DATE_ORACLE;

					String[] fechaConvertida = { fechaYTiempo,
							formatoFechaYTiempo };
					String fechaBD = UtilDTO.cambioValores(
							ConstanteFormatoFechaYHora.ESTRUCTURA_DATE_BD_1,
							fechaConvertida);
					datosConvertidos[i] = fechaBD;

				} else if (ConstanteTipoDatoBD.TIPO_DATE_MYSQL
						.equals(tipoDatos[i])) {
					datosConvertidos[i] = "'" + datos[i] + "'";
				} else if (ConstanteTipoDatoBD.TIPO_TIME_MYSQL
						.equals(tipoDatos[i])) {
					datosConvertidos[i] = "'" + datos[i] + "'";
				} else {
					datosConvertidos[i] = datos[i];
				}
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return datosConvertidos;
	}

	private static String obtenerTiempoOfCalendar(Calendar fechaC) {
		String salida = null;

		String[] tiempoArr = UtilDTO.convertCalendarTiempoToArreglo(fechaC);

		// tiempoArr.length > 3 : hora, minutos y segundos
		if (tiempoArr != null && tiempoArr.length > 2) {
			salida = tiempoArr[0];
			salida += ConstanteGeneral.DOS_PUNTOS;
			salida += tiempoArr[1];
			salida += ConstanteGeneral.DOS_PUNTOS;
			salida += tiempoArr[2];
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}
		return salida;
	}

	private static String obtenerFechaOfCalendar(Calendar fechaC) {

		String salida = null;

		String[] fechaArr = UtilDTO.convertCalendarFechaToArreglo(fechaC);

		// fechaArr.length > 3 : year, mes y dia
		if (fechaArr != null && fechaArr.length > 2) {
			salida = fechaArr[0];
			salida += ConstanteGeneral.GUION_MEDIO;
			salida += fechaArr[1];
			salida += ConstanteGeneral.GUION_MEDIO;
			salida += fechaArr[2];
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return salida;
	}

	private static Calendar convertirStringToCalendar(String cadena) {

		Calendar salida = null;

		if (cadena != null && !ConstanteGeneral.CADENA_VACIA.equals(cadena)) {
			SimpleDateFormat sdf = new SimpleDateFormat(
					ConstanteFormatoFechaYHora.FORMATO_FECHA_Y_TIEMPO_1);
			try {
				Date fecha = sdf.parse(cadena);
				salida = Calendar.getInstance();
				salida.setTime(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}
		return salida;
	}

	private static String[] convertCalendarTiempoToArreglo(Calendar fechaCalend) {
		String[] salida = null;

		if (fechaCalend != null) {
			salida = new String[3];

			int hourOfDay = fechaCalend.get(Calendar.HOUR_OF_DAY); // 24
			int minute = fechaCalend.get(Calendar.MINUTE);
			int second = fechaCalend.get(Calendar.SECOND);

			String hourOfDayStr = Integer.toString(hourOfDay); // 24
			String minuteStr = Integer.toString(minute);
			String secondStr = Integer.toString(second);

			if (ConstanteGeneral.CADENA_NUMERO_CERO.equals(hourOfDayStr)) {
				hourOfDayStr += ConstanteGeneral.CADENA_NUMERO_CERO;
			}
			if (ConstanteGeneral.CADENA_NUMERO_CERO.equals(minuteStr)) {
				minuteStr += ConstanteGeneral.CADENA_NUMERO_CERO;
			}
			if (ConstanteGeneral.CADENA_NUMERO_CERO.equals(secondStr)) {
				secondStr += ConstanteGeneral.CADENA_NUMERO_CERO;
			}

			salida[0] = hourOfDayStr;
			salida[1] = minuteStr;
			salida[2] = secondStr;
		}

		return salida;
	}

	private static String[] convertCalendarFechaToArreglo(Calendar fechaCalend) {
		String[] salida = null;

		if (fechaCalend != null) {
			salida = new String[3];

			int year = fechaCalend.get(Calendar.YEAR);
			int month = fechaCalend.get(Calendar.MONTH) + 1; // Jan = 0, dec
			int dayOfMonth = fechaCalend.get(Calendar.DAY_OF_MONTH);

			salida[0] = Integer.toString(year);
			salida[1] = Integer.toString(month);
			salida[2] = Integer.toString(dayOfMonth);
		}

		return salida;
	}

	private static Date convertStringToFecha(String cadena, String formato) {

		Date fechaParseada = null;

		if (cadena != null && !ConstanteGeneral.CADENA_VACIA.equals(cadena)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(formato);
				fechaParseada = sdf.parse(cadena);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("La fecha es nula");
		}

		return fechaParseada;
	}

	public static String convertirFechaToString(Calendar calendario,
			String formatoFecha) {

		String salida = null;

		if (calendario != null) {
			if (formatoFecha != null
					&& !ConstanteGeneral.CADENA_VACIA.equals(formatoFecha)) {

				Date fecha = calendario.getTime();

				SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
				salida = sdf.format(fecha);
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}

		return salida;
	}

	public static String convertirFechaYHoraToString(Calendar calendario,
			String formatoFechaYTiempo) {
		String salida = null;

		if (calendario != null) {
			if (formatoFechaYTiempo != null
					&& !ConstanteGeneral.CADENA_VACIA
							.equals(formatoFechaYTiempo)) {
				Date fecha = calendario.getTime();

				SimpleDateFormat sdf = new SimpleDateFormat(formatoFechaYTiempo);
				salida = sdf.format(fecha);
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}

		return salida;
	}

	public static void main(String[] args) {
		String formatoDateBD = ConstanteFormatoFechaYHora.FORMATO_FECHA_2;
		Calendar fecha = Calendar.getInstance();
		String salida = UtilDTO.convertirFechaToString(fecha, formatoDateBD);

		System.out.println("Salida: " + salida);
	}

	public static String construirNombreYValorPorCaracter(String[] nombres,
			String[] valores, String separador) {

		String salida = null;

		if (nombres != null && valores != null && separador != null) {
			int nroNombres = nombres.length;
			int nroValores = valores.length;

			if (nroNombres > 0 && nroValores > 0 && nroNombres == nroValores) {
				salida = ConstanteGeneral.CADENA_VACIA;
				String parI = ConstanteGeneral.CADENA_VACIA;
				for (int i = 0; i < nroNombres; i++) {

					MessageFormat mf = new MessageFormat(
							ConstanteEstructuraQueriesSQL.PAR_NOMBRE_Y_VALOR);
					String nombreI = nombres[i];
					String valorI = valores[i];
					String[] datos = { nombreI, ConstanteGeneral.IGUAL, valorI };
					parI = mf.format(datos);
					salida += parI;

					if (i != nroNombres - 1) {
						salida += separador;
					}
				}
			} else {
				System.out.println(ConstanteMensajes.NO_COINCIDEN_DATOS);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}

		return salida;
	}

	public static String construirArrayToStringSeparado(String[] valores,
			String separador) {
		String salida = null;

		if (valores != null && separador != null) {
			int nroValores = valores.length;
			salida = ConstanteGeneral.CADENA_VACIA;
			if (nroValores > 0) {
				for (int i = 0; i < nroValores; i++) {
					salida += valores[i];
					if (i != nroValores - 1) {
						salida += separador;
					}
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO);
		}
		return salida;
	}

	public static Calendar convertirStringToFechaYHora(String fecha,
			String formatoFechaYHora) {

		Calendar salida = null;

		if (fecha != null && !fecha.isEmpty()) {
			if (formatoFechaYHora != null && !formatoFechaYHora.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat(formatoFechaYHora);
				Date salidaD;
				try {
					salidaD = sdf.parse(fecha);
					salida = Calendar.getInstance();
					salida.setTime(salidaD);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return salida;
	}

	public static List<Calendar> obtenerDiasDeLaSemana(Calendar fecha) {

		List<Calendar> listaFechasSemana = null;

		if (fecha != null) {

			listaFechasSemana = new ArrayList<Calendar>();

			Calendar lunes = (Calendar) fecha.clone();
			lunes.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			lunes.set(Calendar.HOUR_OF_DAY, 0);
			lunes.set(Calendar.MINUTE, 0);
			lunes.set(Calendar.SECOND, 0);

			listaFechasSemana.add(lunes);

			int nroDias = 1;
			while (nroDias <= 6) {
				Calendar fechaX = (Calendar) lunes.clone();
				fechaX.add(Calendar.DATE, nroDias);
				nroDias++;
				listaFechasSemana.add(fechaX);
			}
			int ultimaPosicion = listaFechasSemana.size() - 1;

			Calendar domingo = listaFechasSemana.get(ultimaPosicion);
			domingo.set(Calendar.HOUR_OF_DAY, 23);
			domingo.set(Calendar.MINUTE, 59);
			domingo.set(Calendar.SECOND, 59);

			listaFechasSemana.set(ultimaPosicion, domingo);

		} else {
			System.out.println("Fecha es nulo");
		}

		return listaFechasSemana;
	}

	public static String obtenerNombreDia(Calendar fecha) {

		String nombreDia = ConstanteMensajes.DATO_VACIO;

		if (fecha != null) {
			int nroDia = fecha.get(Calendar.DAY_OF_WEEK);

			switch (nroDia) {
			case 1:
				nombreDia = "Domingo";
				break;
			case 2:
				nombreDia = "Lunes";
				break;
			case 3:
				nombreDia = "Martes";
				break;
			case 4:
				nombreDia = "Miercoles";
				break;
			case 5:
				nombreDia = "Jueves";
				break;
			case 6:
				nombreDia = "Viernes";
				break;
			case 7:
				nombreDia = "Sabado";
				break;
			}

		} else {
			System.out.println("Fecha Nula");
		}

		return nombreDia;
	}

	public static String convertirObjetoToJSONStr(Object objeto) {

		String salida = "";

		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer = mapper.writer();
		ObjectWriter objectWritter = writer.withDefaultPrettyPrinter();

		try {
			salida = objectWritter.writeValueAsString(objeto);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return salida;
	}

	public static String convertirDateToString(java.sql.Date fecha,
			String formatoFecha) {

		String salida = null;

		if (fecha != null) {
			if (formatoFecha != null && !formatoFecha.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat(formatoFecha);
				salida = sdf.format(fecha);
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return salida;
	}

	public static String convertirTimeToString(Time hora, String formatoTiempo) {

		String salida = null;

		if (hora != null) {
			if (formatoTiempo != null && !formatoTiempo.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat(formatoTiempo);
				salida = sdf.format(hora);
			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
			}
		} else {
			System.out.println(ConstanteMensajes.OBJETO_NULO_O_VACIO);
		}

		return salida;
	}

	public static ArrayList<String> diferenciaPorValores(Tarea nueva, Tarea anterior) {

		ArrayList<String> salida = null;

		if (nueva != null && anterior != null) {
			// Valores 'ANTERIOR'
			String idAnterior = anterior.getId();
			Date fecha_creacionAnterior = anterior.getFecha_creacion();
			Time hora_creacionAnterior = anterior.getHora_creacion();
			Date fecha_modificacionAnterior = anterior.getFecha_modificacion();
			Time hora_modificacionAnterior = anterior.getHora_modificacion();
			String textoAnterior = anterior.getTexto();
			String estadoAnterior = anterior.getEstado();

			boolean isNotNulosAnterior = idAnterior != null
					&& fecha_creacionAnterior != null
					&& hora_creacionAnterior != null
					&& fecha_modificacionAnterior != null
					&& hora_modificacionAnterior != null
					&& textoAnterior != null && estadoAnterior != null;

			// Valores 'NUEVA'
			String idNueva = nueva.getId();
			Date fecha_creacionNueva = nueva.getFecha_creacion();
			Time hora_creacionNueva = nueva.getHora_creacion();
			Date fecha_modificacionNueva = nueva.getFecha_modificacion();
			Time hora_modificacionNueva = nueva.getHora_modificacion();
			String textoNueva = nueva.getTexto();
			String estadoNueva = nueva.getEstado();

			boolean isNotNulosNueva = idNueva != null
					&& fecha_creacionNueva != null
					&& hora_creacionNueva != null
					&& fecha_modificacionNueva != null
					&& hora_modificacionNueva != null && textoNueva != null
					&& estadoNueva != null;

			if (isNotNulosAnterior && isNotNulosNueva) {
				salida = new ArrayList<String>();

				DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
				DateFormat formatoTiempo = new SimpleDateFormat("HH:mm:ss");

				if (!idAnterior.equals(idNueva)) {
					salida.add(idNueva);
				}

				if (!fecha_creacionAnterior.equals(fecha_creacionNueva)) {
					salida.add(formatoFecha.format(fecha_creacionNueva));
				}

				if (!hora_creacionAnterior.equals(hora_creacionNueva)) {
					salida.add(formatoTiempo.format(hora_creacionNueva));
				}

				if (!fecha_modificacionAnterior.equals(fecha_modificacionNueva)) {
					salida.add(formatoFecha.format(fecha_modificacionNueva));
				}

				if (!hora_modificacionAnterior.equals(hora_modificacionNueva)) {
					salida.add(formatoTiempo.format(hora_modificacionNueva));
				}

				if (!textoAnterior.equals(textoNueva)) {
					salida.add(textoNueva);
				}

				if (!estadoAnterior.equals(estadoNueva)) {
					salida.add(estadoNueva);
				}

			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
			}
		}

		return salida;

	}

	public static ArrayList<String> diferenciaPorTipoDatos(Tarea nueva,
			Tarea anterior) {
		ArrayList<String> salida = null;

		if (nueva != null && anterior != null) {
			// Valores 'ANTERIOR'
			String idAnterior = anterior.getId();
			Date fecha_creacionAnterior = anterior.getFecha_creacion();
			Time hora_creacionAnterior = anterior.getHora_creacion();
			Date fecha_modificacionAnterior = anterior.getFecha_modificacion();
			Time hora_modificacionAnterior = anterior.getHora_modificacion();
			String textoAnterior = anterior.getTexto();
			String estadoAnterior = anterior.getEstado();

			boolean isNotNulosAnterior = idAnterior != null
					&& fecha_creacionAnterior != null
					&& hora_creacionAnterior != null
					&& fecha_modificacionAnterior != null
					&& hora_modificacionAnterior != null
					&& textoAnterior != null && estadoAnterior != null;

			// Valores 'NUEVA'
			String idNueva = nueva.getId();
			Date fecha_creacionNueva = nueva.getFecha_creacion();
			Time hora_creacionNueva = nueva.getHora_creacion();
			Date fecha_modificacionNueva = nueva.getFecha_modificacion();
			Time hora_modificacionNueva = nueva.getHora_modificacion();
			String textoNueva = nueva.getTexto();
			String estadoNueva = nueva.getEstado();

			boolean isNotNulosNueva = idNueva != null
					&& fecha_creacionNueva != null
					&& hora_creacionNueva != null
					&& fecha_modificacionNueva != null
					&& hora_modificacionNueva != null && textoNueva != null
					&& estadoNueva != null;

			if (isNotNulosAnterior && isNotNulosNueva) {
				salida = new ArrayList<String>();

				if (!idAnterior.equals(idNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_VARCHAR2);
				}

				if (!fecha_creacionAnterior.equals(fecha_creacionNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_DATE_MYSQL);
				}

				if (!hora_creacionAnterior.equals(hora_creacionNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_TIME_MYSQL);
				}

				if (!fecha_modificacionAnterior.equals(fecha_modificacionNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_DATE_MYSQL);
				}

				if (!hora_modificacionAnterior.equals(hora_modificacionNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_DATE_MYSQL);
				}

				if (!textoAnterior.equals(textoNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_VARCHAR2);
				}

				if (!estadoAnterior.equals(estadoNueva)) {
					salida.add(ConstanteTipoDatoBD.TIPO_VARCHAR2);
				}

			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
			}
		}

		return salida;
	}

	public static ArrayList<String> diferenciaPorColumnas(Tarea nueva, Tarea anterior) {
		ArrayList<String> salida = null;

		if (nueva != null && anterior != null) {
			// Valores 'ANTERIOR'
			String idAnterior = anterior.getId();
			Date fecha_creacionAnterior = anterior.getFecha_creacion();
			Time hora_creacionAnterior = anterior.getHora_creacion();
			Date fecha_modificacionAnterior = anterior.getFecha_modificacion();
			Time hora_modificacionAnterior = anterior.getHora_modificacion();
			String textoAnterior = anterior.getTexto();
			String estadoAnterior = anterior.getEstado();

			boolean isNulosAnterior = idAnterior != null
					&& fecha_creacionAnterior != null
					&& hora_creacionAnterior != null
					&& fecha_modificacionAnterior != null
					&& hora_modificacionAnterior != null
					&& textoAnterior != null && estadoAnterior != null;

			// Valores 'NUEVA'
			String idNueva = nueva.getId();
			Date fecha_creacionNueva = nueva.getFecha_creacion();
			Time hora_creacionNueva = nueva.getHora_creacion();
			Date fecha_modificacionNueva = nueva.getFecha_modificacion();
			Time hora_modificacionNueva = nueva.getHora_modificacion();
			String textoNueva = nueva.getTexto();
			String estadoNueva = nueva.getEstado();

			boolean isNulosNueva = idNueva != null
					&& fecha_creacionNueva != null
					&& hora_creacionNueva != null
					&& fecha_modificacionNueva != null
					&& hora_modificacionNueva != null && textoNueva != null
					&& estadoNueva != null;

			if (isNulosAnterior && isNulosNueva) {
				salida = new ArrayList<String>();

				if (!idAnterior.equals(idNueva)) {
					salida.add(ConstanteTareaBD.IDTAREA);
				}

				if (!fecha_creacionAnterior.equals(fecha_creacionNueva)) {
					salida.add(ConstanteTareaBD.FECHACREACION);
				}

				if (!hora_creacionAnterior.equals(hora_creacionNueva)) {
					salida.add(ConstanteTareaBD.HORACREACION);
				}

				if (!fecha_modificacionAnterior.equals(fecha_modificacionNueva)) {
					salida.add(ConstanteTareaBD.FECHAMODIFICACION);
				}

				if (!hora_modificacionAnterior.equals(hora_modificacionNueva)) {
					salida.add(ConstanteTareaBD.HORAMODIFICACION);
				}

				if (!textoAnterior.equals(textoNueva)) {
					salida.add(ConstanteTareaBD.TEXTO);
				}

				if (!estadoAnterior.equals(estadoNueva)) {
					salida.add(ConstanteTareaBD.ESTADO);
				}

			} else {
				System.out.println(ConstanteMensajes.OBJETO_NULO);
			}
		}

		return salida;
	}
}
