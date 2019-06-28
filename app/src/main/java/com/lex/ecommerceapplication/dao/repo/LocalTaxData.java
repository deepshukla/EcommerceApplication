package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.Tax;

import java.util.List;

import javax.inject.Inject;
/**
 * local data source.
 * @author DeepS
 */
public class LocalTaxData implements DataSource<Tax>
{

    AppDatabase appDatabase;

    @Inject
    public LocalTaxData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(Tax tax)
    {
        appDatabase.taxDataDao().insert(tax);
    }

    @Override
    public void addItems(List<Tax> items)
    {

    }
}
