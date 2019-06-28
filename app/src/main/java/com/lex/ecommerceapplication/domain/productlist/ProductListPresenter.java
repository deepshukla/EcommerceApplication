package com.lex.ecommerceapplication.domain.productlist;

import com.lex.ecommerceapplication.dao.repo.Repository;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Product list presenter
 *
 * @author DeepS
 */
public class ProductListPresenter implements Contracts.Presenter
{
    private Contracts.View productListView;
    private Repository repository;
    private CompositeDisposable compositeDisposable;

    ProductListPresenter(Contracts.View productListView, Repository repository)
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

    @Override
    public void fetchProductByCategory(int categoryId)
    {
        showLoading();

        Disposable disposable = repository.productData().getProductDetails(categoryId).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productListView::populateData, productListView::onError);
        compositeDisposable.add(disposable);

    }

    @Override
    public void sortByMostViewed()
    {
        List<ProductDetails> productDetailsList = productListView.getProductList();
        Collections.sort(productDetailsList, new ProductDetails.MostViewedComparator());
        productListView.populateData(productDetailsList);
    }

    @Override
    public void sortByMostOrdered()
    {
        List<ProductDetails> productDetailsList = productListView.getProductList();
        Collections.sort(productDetailsList, new ProductDetails.MostOrderedComparator());
        productListView.populateData(productDetailsList);
    }

    @Override
    public void sortByMostShared()
    {
        List<ProductDetails> productDetailsList = productListView.getProductList();
        Collections.sort(productDetailsList, new ProductDetails.MostSharedComparator());
        productListView.populateData(productDetailsList);
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
