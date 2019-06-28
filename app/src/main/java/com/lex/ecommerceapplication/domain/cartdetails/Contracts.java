package com.lex.ecommerceapplication.domain.cartdetails;

import com.lex.ecommerceapplication.model.CartSummary;
import com.lex.ecommerceapplication.model.roomentities.relation.CartDetail;

import java.util.List;

/**
 * Contract for view and presenter
 *
 * @author DeepS
 */
interface Contracts
{

    interface View
    {
        void initView();

        void populateData(List<CartDetail> categoryDTOList, CartSummary cartSummary);

        void onError(Throwable throwable);

        void showLoading();

        void hideLoading();

        void removeFromCart(CartDetail cartDetail);

        void productRemoved();
    }

    interface Presenter
    {
        void init();

        void fetchCartProductDetails();

        void showLoading();

        void hideLoading();

        void onDetach();
    }
}
