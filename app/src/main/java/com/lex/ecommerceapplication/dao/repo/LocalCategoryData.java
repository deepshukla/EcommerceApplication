package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.Category;
import com.lex.ecommerceapplication.model.roomentities.relation.CategoryAndMapping;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class LocalCategoryData implements DataSource<Category>
{

    AppDatabase appDatabase;

    @Inject
    public LocalCategoryData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(Category category)
    {
        appDatabase.categoryDataDao().insert(category);
    }

    @Override
    public void addItems(List<Category> items)
    {
        appDatabase.categoryDataDao().insertAll(items);
    }

    public Single<List<Category>> getCategories()
    {
        return appDatabase.categoryDataDao().getCategories();
    }

    public Single<List<Category>> getParentCategories()
    {
        return appDatabase.categoryDataDao().getParentCategories();
    }

    public Single<List<Category>> getParentSubCategoriesByParentCategoryId(int parentCategoryId)
    {
        return appDatabase.categoryDataDao().getParentSubCategoriesByParentCategoryId(parentCategoryId);
    }

    public Single<List<CategoryAndMapping>> getChildSubCategoriesByParentCategoryId(int parentCategoryId)
    {
        return appDatabase.categoryDataDao().getChildSubCategoriesByParentCategoryId(parentCategoryId);
    }

}
