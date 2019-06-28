package com.lex.ecommerceapplication.model.roomentities.relation;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.lex.ecommerceapplication.model.roomentities.Product;
import com.lex.ecommerceapplication.model.roomentities.Variant;

import java.util.List;

/**
 * Product and variant
 *
 * @author DeepS
 */
public class ProductAndVariant
{
    @Embedded
    public Product product;

    @Relation(parentColumn = "id", entityColumn = "product_id")
    public List<Variant> variants;
}
