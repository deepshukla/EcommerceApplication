package com.lex.ecommerceapplication.domain.categorylist;

import com.lex.ecommerceapplication.domain.categorylist.adapter.CategoryGroup;
import com.lex.ecommerceapplication.model.response.CategoryDTO;

import java.util.List;

/**
 * Contract for view and presenter
 *
 * @author DeepS
 */
public interface Contracts
{

    interface View
    {
        void initView();

        void populateData(List<CategoryDTO> categoryDTOList);

        void onCategoryItemSelected(CategoryDTO category);

        void onError(Throwable throwable);

        void showLoading();

        void hideLoading();

        void populateSubCategories(List<CategoryGroup> categoryGroups);
    }

    interface Presenter
    {
        void init();

        void fetchCategoryAndProductData();

        void showLoading();

        void hideLoading();
        void onDetach();

    }
}
