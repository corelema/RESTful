package com.cocorporation.restfuljaxb.data.dao;

import java.util.HashMap;
import java.util.Map;

import com.cocorporation.restfuljaxb.data.model.Data;

public enum DataDao {
	instance;

	private Map<String, Data> contentProvider = new HashMap<String, Data>();

	private DataDao() {

		Data todo = new Data("1", "Data1 summary");
		todo.setDescription("Data1 description");
		contentProvider.put("1", todo);
		todo = new Data("2", "Data2 summary");
		todo.setDescription("Data2 description");
		contentProvider.put("2", todo);

	}

	public Map<String, Data> getModel() {
		return contentProvider;
	}

}
