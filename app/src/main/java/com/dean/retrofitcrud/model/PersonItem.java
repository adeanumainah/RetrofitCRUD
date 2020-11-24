package com.dean.retrofitcrud.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonItem{

	@SerializedName("image")
	@Expose
	private String image;

	@SerializedName("price")
	@Expose
	private String price;

	@SerializedName("name")
	@Expose
	private String name;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("desc")
	@Expose
	private String desc;

	public PersonItem(){

	}

	public PersonItem(int id, String name, String price, String desc, String image){
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.desc = desc;
	}

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDesc(String desc){
		this.desc = desc;
	}

	public String getDesc(){
		return desc;
	}
}