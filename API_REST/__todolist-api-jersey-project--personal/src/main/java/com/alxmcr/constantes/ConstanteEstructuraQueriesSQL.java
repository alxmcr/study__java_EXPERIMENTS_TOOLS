package com.alxmcr.constantes;

public class ConstanteEstructuraQueriesSQL {
	public static final String SELECT = "SELECT {0} FROM {1}.{2}";
	public static final String SELECT_CON_WHERE = "SELECT {0} FROM {1}.{2} WHERE {3}";
	public static final String UPDATE_CON_WHERE = "UPDATE {0}.{1} SET {2} WHERE {3}";
	public static final String INSERT = "INSERT INTO {0}.{1} ({2}) VALUES ({3})";
	public static final String BETWEEN_DE_CAMPO = "{0} BETWEEN {1} AND {2}";
	public static final String PAR_NOMBRE_Y_VALOR = "{0} {1} {2}";
}
