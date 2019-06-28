package com.lex.ecommerceapplication.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * product DTO
 *
 * @author DeepS
 */
public class ProductDTO
{
	@SerializedName("id")
	@Expose
	private int    id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("date_added")
	@Expose
	private String dateAdded;
	@SerializedName("variants")
	@Expose
	private List<VariantDTO> variants = null;
	@SerializedName("tax")
	@Expose
	private TaxDTO tax;


	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDateAdded()
	{
		return dateAdded;
	}

	public void setDateAdded(String dateAdded)
	{
		this.dateAdded = dateAdded;
	}

	public List<VariantDTO> getVariants()
	{
		return variants;
	}

	public void setVariants(List<VariantDTO> variants)
	{
		this.variants = variants;
	}

	public TaxDTO getTax()
	{
		return tax;
	}

	public void setTax(TaxDTO tax)
	{
		this.tax = tax;
	}
}