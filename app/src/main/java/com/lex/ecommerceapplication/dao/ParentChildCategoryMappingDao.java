package com.lex.ecommerceapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.lex.ecommerceapplication.model.roomentities.ParentChildCategoryMapping;

import java.util.List;

/**
 * Parent Child category mapping DAO
 *
 * @author DeepS
 */
@Dao
public interface ParentChildCategoryMappingDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ParentChildCategoryMapping parentChildCategoryMapping);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ParentChildCategoryMapping> parentChildCategoryMappingList);
}
