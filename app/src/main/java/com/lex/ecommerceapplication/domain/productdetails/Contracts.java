package com.lex.ecommerceapplication.domain.productdetails;

import com.lex.ecommerceapplication.model.response.VariantDTO;
import com.lex.ecommerceapplication.model.roomentities.Variant;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.ArrayList;
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

        void onError(Throwable throwable);

        void showLoading();

        void hideLoading();

        void onSuccess();

        void onVariantSelected(VariantDTO variantDTO);

        void onVariantFetched(ArrayList<VariantDTO> variants);

        void onAddError(Throwable e);
    }

    interface Presenter
    {
        void init();

        void showLoading();

        void hideLoading();

        void addtoCart(ProductDetails productDetails);
        void onDetach();

    }
}
