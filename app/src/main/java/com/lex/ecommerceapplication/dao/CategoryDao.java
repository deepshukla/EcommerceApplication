package com.lex.ecommerceapplication.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.lex.ecommerceapplication.model.roomentities.Category;
import com.lex.ecommerceapplication.model.roomentities.relation.CategoryAndMapping;

import java.util.List;

import io.reactivex.Single;

/**
 * Category DAO
 *
 * @author DeepS
 */
@Dao
public interface CategoryDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Category> categories);

    @Query("SELECT DISTINCT Category.* FROM Category INNER JOIN ParentChildCategoryMapping ON" +
            " Category.category_id = ParentChildCategoryMapping.parent_id " +
            "WHERE Category.category_id NOT IN (SELECT DISTINCT ParentChildCategoryMapping.child_id FROM ParentChildCategoryMapping)")
    Single<List<Category>> getParentCategories();

    @Query("SELECT * FROM Category")
    Single<List<Category>> getCategories();

    @Query("SELECT Category.* FROM Category INNER JOIN ParentChildCategoryMapping ON" +
            " Category.category_id = ParentChildCategoryMapping.child_id WHERE ParentChildCategoryMapping.parent_id=:parentCategoryId")
    Single<List<Category>> getParentSubCategoriesByParentCategoryId(int parentCategoryId);


    @Query("SELECT Category.*,ParentChildCategoryMapping.parent_id FROM Category " +
            "INNER JOIN ParentChildCategoryMapping ON Category.category_id = ParentChildCategoryMapping.child_id " +
            "WHERE ParentChildCategoryMapping.parent_id IN (SELECT ParentChildCategoryMapping.child_id " +
            "FROM ParentChildCategoryMapping WHERE ParentChildCategoryMapping.parent_id=:parentCategoryId)")
    Single<List<CategoryAndMapping>> getChildSubCategoriesByParentCategoryId(int parentCategoryId);


}
