package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.ParentChildCategoryMapping;

import java.util.List;

import javax.inject.Inject;

/**
 * local data source.
 * @author DeepS
 */
public class LocalParentChildCategoryMappingData implements DataSource<ParentChildCategoryMapping>
{

    AppDatabase appDatabase;

    @Inject
    public LocalParentChildCategoryMappingData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(ParentChildCategoryMapping parentChildCategoryMapping)
    {
        appDatabase.parentChildCategoryMappingDataDao().insert(parentChildCategoryMapping);
    }

    @Override
    public void addItems(List<ParentChildCategoryMapping> items)
    {
    }
}
