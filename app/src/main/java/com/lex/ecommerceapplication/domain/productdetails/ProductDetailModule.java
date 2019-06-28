package com.lex.ecommerceapplication.domain.productdetails;

import com.lex.ecommerceapplication.api.ApiService;
import com.lex.ecommerceapplication.dao.repo.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Product detail module
 *
 * @author DeepS
 */
@Module
public class ProductDetailModule
{

    private final Contracts.View productListView;

    public ProductDetailModule(Contracts.View productListView)
    {
        this.productListView = productListView;
    }

    @Provides
    @ProductDetailScope
    ProductDetailPresenter provideProductDetailPresenter(Repository repository)
    {
        return new ProductDetailPresenter(productListView, repository);
    }
}
