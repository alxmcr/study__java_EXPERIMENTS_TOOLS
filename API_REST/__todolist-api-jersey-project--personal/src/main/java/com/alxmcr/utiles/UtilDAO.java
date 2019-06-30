package com.alxmcr.utiles;

import java.util.List;

public class UtilDAO {

	public static String construirSetSQLs(List<String> nombresColumnas,
			String[] datosConvertidos) {
		StringBuffer salida = new StringBuffer();

		if (datosConvertidos != null && nombresColumnas != null) {
			int nroValores = datosConvertidos.length;
			int nroColumnas = nombresColumnas.size();

			if (nroColumnas == nroValores) {
				for (int i = 0; i < nroValores; i++) {

					salida.append(nombresColumnas.get(i));
					salida.append(" = ");
					salida.append(datosConvertidos[i]);
					if (i != nroValores - 1) {
						salida.append(" , ");
					}
				}
			}
		}

		return salida.toString();
	}
}
