package com.dean.retrofitcrud.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseProduct {

	@SerializedName("person")
	private List<PersonItem> person;

	@SerializedName("error")
	private boolean error;

	@SerializedName("status")
	private int status;

	public void setPerson(List<PersonItem> person){
		this.person = person;
	}

	public List<PersonItem> getPerson(){
		return person;
	}

	public void setError(boolean error){
		this.error = error;
	}

	public boolean isError(){
		return error;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}
}