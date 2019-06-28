package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Variant entity.
 *
 * @author DeepS
 */
@ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id")
@Entity(tableName = "Variant")
public class Variant
{
    @NotNull

    @PrimaryKey
    @ColumnInfo(name = "variant_id")
    private int variantId;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "size")
    private int size;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "product_id")
    private int productId;

    public Variant()
    {
    }

    public Variant(@NotNull int id, String color, int size, int price, int productId)
    {
        this.variantId = id;
        this.color = color;
        this.size = size;
        this.price = price;
        this.productId = productId;
    }

    @NotNull
    public int getVariantId()
    {
        return variantId;
    }

    public void setVariantId(@NotNull int id)
    {
        this.variantId = id;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }
}