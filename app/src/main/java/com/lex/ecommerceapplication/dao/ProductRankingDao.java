package com.lex.ecommerceapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.lex.ecommerceapplication.model.roomentities.ProductRanking;

import java.util.List;
/**
 * Product Ranking detail DAO
 *
 * @author DeepS
 */
@Dao
public interface ProductRankingDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductRanking productRanking);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ProductRanking> productRankingList);
}
