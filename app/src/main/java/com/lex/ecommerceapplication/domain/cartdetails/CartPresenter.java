package com.lex.ecommerceapplication.domain.cartdetails;

import com.lex.ecommerceapplication.dao.repo.Repository;
import com.lex.ecommerceapplication.model.CartSummary;
import com.lex.ecommerceapplication.model.roomentities.relation.CartDetail;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Cart presenter.
 *
 * @author DeepS
 */
class CartPresenter implements Contracts.Presenter
{
    private Contracts.View cartView;
    private Repository repository;
    private CartSummary cartSummary;
    private CompositeDisposable compositeDisposable;

    CartPresenter(Contracts.View cartView, Repository repository)
    {
        super();
        this.cartView = cartView;
        this.repository = repository;
        cartSummary = new CartSummary();
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void init()
    {
        cartView.initView();
    }

    @Override
    public void fetchCartProductDetails()
    {
        showLoading();

        Disposable disposable = repository.cardData().getCartDetails()
                .subscribeOn(Schedulers.io())
                .map(cartDetails -> {

                    for (CartDetail cartDetail : cartDetails)
                    {
                        int price = cartDetail.variant.getPrice();
                        cartSummary.setCartTotal(cartSummary.getCartTotal() + price);
                        float taxRate = cartDetail.tax.getValue();
                        int tax = (int) ((price * taxRate) / 100);
                        cartSummary.setEstimatedTax(cartSummary.getEstimatedTax() + tax);
                        cartSummary.setTotalPayable(cartSummary.getTotalPayable() + price + tax);
                    }
                    return cartDetails;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, cartView::onError);
        compositeDisposable.add(disposable);

    }

    private void onSuccess(List<CartDetail> cartDetails)
    {
        cartView.populateData(cartDetails, cartSummary);
    }


    @Override
    public void showLoading()
    {
        cartView.showLoading();
    }

    @Override
    public void hideLoading()
    {
        cartView.hideLoading();
    }

    public void removeFromCart(int cartId)
    {
        Completable.fromAction(() -> repository.cardData().removeFromCart(cartId)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver()
                {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                    }

                    @Override
                    public void onComplete()
                    {
                        cartView.productRemoved();
                        fetchCartProductDetails();
                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        cartView.onError(e);
                    }
                });
    }

    @Override
    public void onDetach()
    {
        compositeDisposable.clear();
    }
}
