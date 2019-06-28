package com.lex.ecommerceapplication.domain.productlist;

import com.lex.ecommerceapplication.api.ApiService;
import com.lex.ecommerceapplication.dao.repo.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Product list module
 *
 * @author DeepS
 */
@Module
public class ProductListModule
{

    private final Contracts.View productListView;

    public ProductListModule(Contracts.View productListView)
    {
        this.productListView = productListView;
    }

    @Provides
    @ProductListScope
    ProductListPresenter provideProductListPresenter( Repository repository)
    {
        return new ProductListPresenter(productListView, repository);
    }
}
