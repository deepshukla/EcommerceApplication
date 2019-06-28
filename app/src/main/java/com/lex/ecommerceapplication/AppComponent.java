package com.lex.ecommerceapplication;

import com.lex.ecommerceapplication.api.ApiModule;
import com.lex.ecommerceapplication.domain.cartdetails.CartComponent;
import com.lex.ecommerceapplication.domain.cartdetails.CartModule;
import com.lex.ecommerceapplication.domain.categorylist.CategoryComponent;
import com.lex.ecommerceapplication.domain.categorylist.CategoryModule;
import com.lex.ecommerceapplication.domain.productdetails.ProductDetailComponent;
import com.lex.ecommerceapplication.domain.productdetails.ProductDetailModule;
import com.lex.ecommerceapplication.domain.productlist.ProductListComponent;
import com.lex.ecommerceapplication.domain.productlist.ProductListModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App component
 *
 * @author DeepS
 */
@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface AppComponent
{
    void inject(BaseApplication baseApplication);

    CategoryComponent newCategoryComponent(CategoryModule categoryModule);

    ProductListComponent newProductListComponent(ProductListModule categoryModule);

    ProductDetailComponent newProductDetailComponent(ProductDetailModule categoryModule);

    CartComponent newCartComponent(CartModule cartModule);
}