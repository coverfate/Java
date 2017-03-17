
package com.cover.utils;

import org.apache.http.NameValuePair;


public class CFParamPair implements NameValuePair {
	private String name;
	private String value;

	public CFParamPair(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

}
