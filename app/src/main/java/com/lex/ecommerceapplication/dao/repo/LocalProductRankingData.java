package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.ProductRanking;

import java.util.List;

import javax.inject.Inject;
/**
 * local data source.
 * @author DeepS
 */
public class LocalProductRankingData implements DataSource<ProductRanking>
{

    AppDatabase appDatabase;

    @Inject
    public LocalProductRankingData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(ProductRanking productRanking)
    {
        appDatabase.productRankingDataDao().insert(productRanking);
    }

    @Override
    public void addItems(List<ProductRanking> items)
    {

    }
}
