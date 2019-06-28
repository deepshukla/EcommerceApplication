package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Category entity.
 *
 * @author DeepS
 */
@Entity(tableName = "Category")
public class Category
{
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "category_name")
    private String CategoryName;


    public Category()
    {
    }

    public Category(@NotNull int id, String categoryName)
    {
        this.categoryId = id;
        CategoryName = categoryName;
    }

    @NotNull
    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(@NotNull int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return CategoryName;
    }

    public void setCategoryName(String categoryName)
    {
        CategoryName = categoryName;
    }
}