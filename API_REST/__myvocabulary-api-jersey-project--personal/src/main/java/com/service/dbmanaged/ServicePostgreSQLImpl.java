package com.service.dbmanaged;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.dao.dbmanaged.PostgreSQLDao;

public class ServicePostgreSQLImpl implements ServicePostgreSQL {

	@Autowired
	@Qualifier("daoPostgreSQL")
	private PostgreSQLDao daoPostgreSQL;
	
	@Override
	public int countProcessConnections() throws Exception {
		return daoPostgreSQL.countProcessConnections();
	}

	@Override
	public List<Integer> listPIDPostgreSQL() throws Exception {
		return daoPostgreSQL.listPIDPostgreSQL();
	}

}
