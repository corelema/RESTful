package com.cocorporation.restfuljaxb.data.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.cocorporation.restfuljaxb.data.dao.DataDao;
import com.cocorporation.restfuljaxb.data.model.Data;

// Will map the resource to the URL datas
@Path("/datas")
public class DataEntryPoint {

	// Allows to insert contextual objects into the class,
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	// Return the list of datas to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Data> getDatasBrowser() {
		List<Data> datas = new ArrayList<Data>();
		datas.addAll(DataDao.instance.getModel().values());
		return datas;
	}

	// Return the list of datas for applications
	@GET
	//@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces("application/json")
	public List<Data> getDatas() {
		List<Data> datas = new ArrayList<Data>();
		datas.addAll(DataDao.instance.getModel().values());
		return datas;
	}
	
	/*TRY TO ADAPT THIS:
	@Produces(MediaType.APPLICATION_JSON)
  	@Consumes(MediaType.APPLICATION_JSON)
  	public JSONObject sayPlainTextHello(JSONObject inputJsonObj) throws Exception {

    String input = (String) inputJsonObj.get("input");
    String output = "The input you sent is :" + input;
    JSONObject outputJsonObj = new JSONObject();
    outputJsonObj.put("output", output);

    return outputJsonObj;
    */

	// retuns the number of datas
	// Use http://localhost:8080/de.vogella.jersey.data/rest/datas/count
	// to get the total number of records
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = DataDao.instance.getModel().size();
		return String.valueOf(count);
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newData(@FormParam("id") String id,
			@FormParam("summary") String summary,
			@FormParam("description") String description,
			@Context HttpServletResponse servletResponse) throws IOException {
		Data data = new Data(id, summary);
		if (description != null) {
			data.setDescription(description);
		}
		DataDao.instance.getModel().put(id, data);

		servletResponse.sendRedirect("../create_data.html");
	}

	// Defines that the next path parameter after datas is
	// treated as a parameter and passed to the DataResources
	// Allows to type http://localhost:8080/de.vogella.jersey.data/rest/datas/1
	// 1 will be treaded as parameter data and passed to DataResource
	@Path("{data}")
	public DataEntryPointWithParameter getData(@PathParam("data") String id) {
		return new DataEntryPointWithParameter(uriInfo, request, id);
	}

}