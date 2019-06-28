package com.lex.ecommerceapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.lex.ecommerceapplication.model.roomentities.ProductTax;

import java.util.List;
/**
 * Product Tax DAO - as there can be many to many relation
 *
 * @author DeepS
 */
@Dao
public interface ProductTaxDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductTax productTax);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductTax> productTaxList);

}
