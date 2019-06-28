package com.lex.ecommerceapplication.domain.productdetails;

import com.lex.ecommerceapplication.dao.repo.Repository;
import com.lex.ecommerceapplication.model.response.VariantDTO;
import com.lex.ecommerceapplication.model.roomentities.Cart;
import com.lex.ecommerceapplication.model.roomentities.Variant;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.ArrayList;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Product detail presenter
 *
 * @author DeepS
 */
public class ProductDetailPresenter implements Contracts.Presenter
{
    private Contracts.View productListView;
    private Repository repository;
    private CompositeDisposable compositeDisposable;

    ProductDetailPresenter(Contracts.View productListView, Repository repository)
    {
        super();
        this.productListView = productListView;
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void init()
    {
        productListView.initView();

    }

    void getProductVariant(int productId)
    {
        Disposable disposable = repository.variantData().getProductVariant(productId)
                .subscribeOn(Schedulers.io())
                .map(variants ->
                {
                    ArrayList<VariantDTO> variantDTOList = new ArrayList<>();
                    for (Variant variant : variants)
                    {
                        variantDTOList.add(new VariantDTO(variant.getVariantId(), variant.getColor(), variant.getSize(), variant.getPrice()));
                    }
                    return variantDTOList;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productListView::onVariantFetched, productListView::onError);

        compositeDisposable.add(disposable);
    }

    @Override
    public void addtoCart(ProductDetails productDetails)
    {
        Completable.fromAction(() -> {

            Cart cart = new Cart(productDetails.getVariantId(), productDetails.getId(), 1);

            repository.cardData().addItem(cart);

        }).subscribeOn(Schedulers.io())
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
                        productListView.onSuccess();

                    }

                    @Override
                    public void onError(Throwable e)
                    {
                        productListView.onAddError(e);
                    }
                });

    }

    @Override
    public void showLoading()
    {
        productListView.showLoading();
    }

    @Override
    public void hideLoading()
    {
        productListView.hideLoading();
    }


    @Override
    public void onDetach()
    {
        compositeDisposable.clear();
    }
}
