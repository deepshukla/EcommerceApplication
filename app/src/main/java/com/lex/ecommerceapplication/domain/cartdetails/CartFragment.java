package com.lex.ecommerceapplication.domain.cartdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lex.ecommerceapplication.BaseApplication;
import com.lex.ecommerceapplication.BaseFragment;
import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.model.CartSummary;
import com.lex.ecommerceapplication.model.roomentities.relation.CartDetail;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Cart Fragment.
 *
 * @author DeepS
 */
public class CartFragment extends BaseFragment implements Contracts.View
{
    @BindView(R.id.rv_movie_list)
    RecyclerView rvCategoryList;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_info)
    AppCompatTextView tvInfo;
    @BindView(R.id.tv_total)
    TextView tvTotal;

    @BindView(R.id.ll_summary)
    LinearLayout llSummary;
    @BindView(R.id.tv_no_item)
    TextView tvNoItem;
    Unbinder unbinder;

    @Inject
    CartPresenter presenter;

    private LinearLayoutManager linearLayoutManager;

    private CartAdapter cartAdapter;

    public static CartFragment newInstance()
    {
        Bundle args = new Bundle();
        CartFragment fragment = new CartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent()
                .newCartComponent(new CartModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        presenter.init();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        unbinder.unbind();
    }

    //View methods
    @Override
    public void initView()
    {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCategoryList.setLayoutManager(linearLayoutManager);
        //cartAdapter = new cartAdapter(getContext(), this);
        //rvCategoryList.setAdapter(categoryListAdapter);
        presenter.fetchCartProductDetails();
    }

    @Override
    public void populateData(List<CartDetail> categoryGroupList, CartSummary cartSummary)
    {
        if (categoryGroupList != null && categoryGroupList.size() != 0)
        {
            cartAdapter = new CartAdapter(getActivity(), categoryGroupList, cartSummary, this);
            rvCategoryList.setAdapter(cartAdapter);
            tvTotal.setText(getString(R.string.str_rs, cartSummary.getTotalPayable()));
            hideLoading();
        } else
            showNoItem();

    }

    void showNoItem()
    {
        tvNoItem.setVisibility(View.VISIBLE);
        llSummary.setVisibility(View.GONE);
        rvCategoryList.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvInfo.setVisibility(View.GONE);
    }

    @Override
    public void onError(Throwable throwable)
    {
        hideLoading();
        Snackbar.make(tvInfo, R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading()
    {
        tvNoItem.setVisibility(View.GONE);
        llSummary.setVisibility(View.GONE);
        rvCategoryList.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        tvInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        tvNoItem.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        tvInfo.setVisibility(View.GONE);
        rvCategoryList.setVisibility(View.VISIBLE);
        llSummary.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeFromCart(CartDetail cartDetail)
    {
        presenter.removeFromCart(cartDetail.cart.getCartId());
    }

    @OnClick(R.id.ll_total)
    public void showTotalDetails()
    {
        rvCategoryList.smoothScrollToPosition(cartAdapter.getItemCount());
    }

    @Override
    public void productRemoved()
    {
        Snackbar.make(tvInfo, R.string.removed_from_cart, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenter.onDetach();
    }
}
