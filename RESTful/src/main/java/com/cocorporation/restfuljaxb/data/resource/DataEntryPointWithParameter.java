package com.cocorporation.restfuljaxb.data.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.cocorporation.restfuljaxb.data.dao.DataDao;
import com.cocorporation.restfuljaxb.data.model.Data;

public class DataEntryPointWithParameter {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public DataEntryPointWithParameter(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Data getData() {
		Data data = DataDao.instance.getModel().get(id);
		if (data == null)
			throw new RuntimeException("Get: Data with " + id + " not found");
		return data;
	}

	// for the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public Data getDataHTML() {
		Data data = DataDao.instance.getModel().get(id);
		if (data == null)
			throw new RuntimeException("Get: Data with " + id + " not found");
		return data;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putData(JAXBElement<Data> data) {
		Data c = data.getValue();
		return putAndGetResponse(c);
	}

	@DELETE
	public void deleteData() {
		Data c = DataDao.instance.getModel().remove(id);
		if (c == null)
			throw new RuntimeException("Delete: Data with " + id + " not found");
	}

	private Response putAndGetResponse(Data data) {
		Response res;
		if (DataDao.instance.getModel().containsKey(data.getId())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		DataDao.instance.getModel().put(data.getId(), data);
		return res;
	}

}