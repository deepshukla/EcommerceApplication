package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Product entity.
 *
 * @author DeepS
 */
@ForeignKey(entity = Category.class, parentColumns = "category_id", childColumns = "id")
@Entity(tableName = "Product")
public class Product
{
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo(name = "date_added")
    private String dateAdded;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    public Product()
    {
    }

    public Product(int id, String productName, String dateAdded, int categoryId)
    {
        this.id = id;
        this.productName = productName;
        this.dateAdded = dateAdded;
        this.categoryId = categoryId;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getDateAdded()
    {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded)
    {
        this.dateAdded = dateAdded;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }
}