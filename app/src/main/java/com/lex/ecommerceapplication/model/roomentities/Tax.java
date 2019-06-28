package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Tax entity.
 *
 * @author DeepS
 */
@Entity(tableName = "Tax")
public class Tax
{
	@NotNull
	@PrimaryKey
	private String name;
	@ColumnInfo(name = "value")
	private float  value;

	public Tax()
	{
	}

	public Tax(@NotNull String name, float value)
	{
		this.name = name;
		this.value = value;
	}

	@NotNull
	public String getName()
	{
		return name;
	}

	public void setName(@NotNull String name)
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