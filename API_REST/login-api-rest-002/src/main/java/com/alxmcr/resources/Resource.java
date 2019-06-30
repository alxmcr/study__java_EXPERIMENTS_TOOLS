package com.alxmcr.resources;

import javax.ws.rs.core.Response;

public interface Resource<T> {
	Response login(T t) throws Exception;
}
