package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Product Tax entity.
 *
 * @author DeepS
 */
@Entity(tableName = "ProductTax")
public class ProductTax
{

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_id")
    public int productId;
    @ColumnInfo(name = "tax_name")
    private String taxName;

    public ProductTax()
    {
    }

    public ProductTax(int productId, String taxName)
    {
        this.productId = productId;
        this.taxName = taxName;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getTaxName()
    {
        return taxName;
    }

    public void setTaxName(String taxName)
    {
        this.taxName = taxName;
    }
}