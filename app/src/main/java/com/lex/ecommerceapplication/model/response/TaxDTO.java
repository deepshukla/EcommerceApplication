package com.lex.ecommerceapplication.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Tax DTO
 *
 * @author DeepS
 */
public class TaxDTO
{
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("value")
	@Expose
	private float  value;


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getValue()
	{
		return value;
	}

	public void setValue(float value)
	{
		this.value = value;
	}
}