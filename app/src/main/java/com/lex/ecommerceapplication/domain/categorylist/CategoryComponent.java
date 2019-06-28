package com.lex.ecommerceapplication.domain.categorylist;

import com.lex.ecommerceapplication.domain.productlist.ProductListFragment;

import dagger.Subcomponent;


/**
 * Created by DeepS
 * Custom scope for category screen, this is a child component of App Component
 * and needs to be smaller in size
 */
@CategoryScope
@Subcomponent(modules = {CategoryModule.class})
public interface CategoryComponent
{
    void inject(CategoryListFragment categoryListFragment);
    void inject(SubCategoryListFragment subCategoryListFragment);

}