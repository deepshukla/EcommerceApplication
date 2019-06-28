package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Cart entity.
 *
 * @author DeepS
 */
@Entity(
        foreignKeys = {
                @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "prod_id"),
                @ForeignKey(entity = Variant.class, parentColumns = "variant_id", childColumns = "cart_id")
        }, tableName = "Cart",

        indices = {@Index(value = {"var_id", "prod_id"},
                unique = true)})

public class Cart
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cart_id")
    private int cartId;

    @ColumnInfo(name = "var_id")
    private int variantId;

    @ColumnInfo(name = "prod_id")
    private int productId;

    @ColumnInfo(name = "quantity")
    private int quantity;


    public Cart()
    {
    }

    public Cart(int variantId, int productId, int quantity)
    {
        this.variantId = variantId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getCartId()
    {
        return cartId;
    }

    public void setCartId(int cartId)
    {
        this.cartId = cartId;
    }

    public int getVariantId()
    {
        return variantId;
    }

    public void setVariantId(int variantId)
    {
        this.variantId = variantId;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}