package com.cocorporation.restfuljaxb.entrypoint;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
// JAX-RS supports an automatic mapping from JAXB annotated class to XML and
// JSON
// Isn't that cool?
public class Todo {
	private String summary;
	private String description;
	//private String nodeRandom;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	/*
	public String getNodeRandom() {
		return nodeRandom;
	}
	
	public void setNodeRandom(String nodeRandom) {
		this.nodeRandom = nodeRandom;
	}*/
}