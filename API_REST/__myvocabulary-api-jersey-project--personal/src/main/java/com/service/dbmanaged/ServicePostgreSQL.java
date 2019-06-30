package com.service.dbmanaged;

import java.util.List;

public interface ServicePostgreSQL {
	int countProcessConnections() throws Exception;
	List<Integer> listPIDPostgreSQL() throws Exception;
}
