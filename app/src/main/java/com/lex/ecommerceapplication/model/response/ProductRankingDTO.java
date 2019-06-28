package com.lex.ecommerceapplication.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Product ranking DTO
 *
 * @author DeepS
 */
public class ProductRankingDTO
{
	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("view_count")
	@Expose
	private int viewCount;
	@SerializedName("order_count")
	@Expose
	private int orderCount;
	@SerializedName("shares")
	@Expose
	private int shares;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getViewCount()
	{
		return viewCount;
	}

	public void setViewCount(int viewCount)
	{
		this.viewCount = viewCount;
	}

	public int getOrderCount()
	{
		return orderCount;
	}

	public void setOrderCount(int orderCount)
	{
		this.orderCount = orderCount;
	}

	public int getShares()
	{
		return shares;
	}

	public void setShares(int shares)
	{
		this.shares = shares;
	}
}