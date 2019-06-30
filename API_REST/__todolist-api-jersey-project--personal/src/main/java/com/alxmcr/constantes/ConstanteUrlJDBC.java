package com.alxmcr.constantes;

public class ConstanteUrlJDBC {
	// jdbc:oracle:thin:@//<host>:<port>/<service_name>
	public static final String JDBC_URL_ORACLE_THIN_SERVICE = "jdbc:oracle:thin:@//{0}:{1}/{2}";
	// jdbc:oracle:thin:@<host>:<port>:<SID>
	public static final String JDBC_URL_ORACLE_THIN_SID = "jdbc:oracle:thin:@{0}:{1}:{2}";
	// jdbc:oracle:thin:@<TNSName>
	public static final String JDBC_URL_ORACLE_THIN_TNSNAME = "jdbc:oracle:thin:@{0}";
	public static final String JDBC_URL_POSTGRES = "jdbc:postgresql:";
	public static final String JDBC_URL_SQL_SERVER = "jdbc:sqlserver:";
	public static final String JDBC_URL_MYSQL = "jdbc:mysql://{0}:{1}/{2}";
}
