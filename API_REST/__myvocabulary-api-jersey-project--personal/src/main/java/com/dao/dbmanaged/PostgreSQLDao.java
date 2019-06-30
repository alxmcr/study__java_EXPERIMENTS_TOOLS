package com.dao.dbmanaged;

import java.util.List;

public interface PostgreSQLDao {
	int countProcessConnections() throws Exception;
	List<Integer> listPIDPostgreSQL() throws Exception;
}
