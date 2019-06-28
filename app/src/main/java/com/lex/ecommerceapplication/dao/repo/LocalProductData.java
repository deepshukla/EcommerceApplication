package com.lex.ecommerceapplication.dao.repo;

import com.lex.ecommerceapplication.database.AppDatabase;
import com.lex.ecommerceapplication.model.roomentities.Product;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductAndVariant;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
/**
 * local data source.
 * @author DeepS
 */
public class LocalProductData implements DataSource<Product>
{

    AppDatabase appDatabase;

    @Inject
    public LocalProductData(AppDatabase appDatabase)
    {
        this.appDatabase = appDatabase;
    }

    @Override
    public void addItem(Product product)
    {
        appDatabase.productDataDao().insert(product);
    }

    @Override
    public void addItems(List<Product> items)
    {

    }

    public Single<List<ProductAndVariant>> getProductByCategory(int categoryId)
    {
        return appDatabase.productDataDao().getProductAndVariant(categoryId);
    }

    public Single<List<ProductAndVariant>> getProductList(int categoryId)
    {
        return appDatabase.productDataDao().getProductList(categoryId);
    }

    public Single<List<ProductDetails>> getProductDetails(int categoryId)
    {
        return appDatabase.productDataDao().getProductDetails(categoryId);
    }
}
