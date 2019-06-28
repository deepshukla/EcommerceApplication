package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.ProductTax;

import java.util.List;

import javax.inject.Inject;
/**
 * local data source.
 * @author DeepS
 */
public class LocalProductTaxData implements DataSource<ProductTax>
{

    AppDatabase appDatabase;

    @Inject
    public LocalProductTaxData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(ProductTax productTax)
    {
        appDatabase.productTaxDataDao().insert(productTax);
    }

    @Override
    public void addItems(List<ProductTax> items)
    {
    }
}
