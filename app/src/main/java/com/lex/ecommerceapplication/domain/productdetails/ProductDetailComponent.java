package com.lex.ecommerceapplication.domain.productdetails;

import dagger.Subcomponent;


/**
 * Created by DeepS
 * Custom scope for product detail screen, this is a child component of App Component
 * and needs to be smaller in size
 */
@ProductDetailScope
@Subcomponent(modules = {ProductDetailModule.class})
public interface ProductDetailComponent
{
    void inject(ProductDetailFragment productDetailFragment);
}