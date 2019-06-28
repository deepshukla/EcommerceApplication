package com.lex.ecommerceapplication.domain.categorylist;

import com.lex.ecommerceapplication.api.ApiService;
import com.lex.ecommerceapplication.dao.repo.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * Category module.
 *
 * @author DeepS
 */
@Module
public class CategoryModule
{
    private final Contracts.View categoryView;

    public CategoryModule(Contracts.View categoryView)
    {
        this.categoryView = categoryView;
    }

    @Provides
    @CategoryScope
    CategoryListPresenter provideCategoryListPresenter(ApiService apiService, Repository repository)
    {
        return new CategoryListPresenter(categoryView, apiService,repository);
    }
}
