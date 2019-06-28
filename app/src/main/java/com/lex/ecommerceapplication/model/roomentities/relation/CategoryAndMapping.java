package com.lex.ecommerceapplication.model.roomentities.relation;

import android.arch.persistence.room.ColumnInfo;

/**
 * Category and mapping details
 *
 * @author DeepS
 */
public class CategoryAndMapping
{
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "category_name")
    private String CategoryName;
    @ColumnInfo(name = "parent_id")
    private int parentId;


    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof CategoryAndMapping)
            return categoryId == ((CategoryAndMapping) obj).categoryId;
        else
            return super.equals(obj);
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
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

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }
}
