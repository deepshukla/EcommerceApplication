package com.lex.ecommerceapplication.domain.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lex.ecommerceapplication.BaseApplication;
import com.lex.ecommerceapplication.BaseFragment;
import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.domain.cartdetails.CartActivity;
import com.lex.ecommerceapplication.domain.productdetails.ProductDetailActivity;
import com.lex.ecommerceapplication.domain.productdetails.ProductDetailFragment;
import com.lex.ecommerceapplication.model.response.CategoryDTO;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Product list fragment
 *
 * @author DeepS
 */
public class ProductListFragment extends BaseFragment implements Contracts.View
{
    @BindView(R.id.rv_movie_list)
    RecyclerView rvCategoryList;

    @BindView(R.id.tv_sort)
    TextView tvSort;

    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_info)
    AppCompatTextView tvInfo;

    Unbinder unbinder;

    @Inject
    ProductListPresenter presenter;

    private GridLayoutManager gridLayoutManager;

    private ProductListAdapter productListAdapter;

    public static final String STR_CATEGORY = "str_category";

    private CategoryDTO categoryDTO;

    public static ProductListFragment newInstance(CategoryDTO categoryDTO)
    {

        Bundle args = new Bundle();
        args.putParcelable(STR_CATEGORY, categoryDTO);
        ProductListFragment fragment = new ProductListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent()
                .newProductListComponent(new ProductListModule(this))
                .inject(this);

        categoryDTO = getArguments().getParcelable(STR_CATEGORY);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_go_to_cart:
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    //View methods
    @Override
    public void initView()
    {
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        rvCategoryList.setLayoutManager(gridLayoutManager);
        productListAdapter = new ProductListAdapter(getActivity(), this);
        rvCategoryList.setAdapter(productListAdapter);
        presenter.fetchProductByCategory(categoryDTO.getId());
    }

    @Override
    public void populateData(List<ProductDetails> productDetailsList)
    {
//        productListAdapter.clear();
        productListAdapter.setAll(productDetailsList);
        rvCategoryList.smoothScrollToPosition(0);
        hideLoading();
    }

    @Override
    public void onCategoryItemSelected(ProductDetails result)
    {
        Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
        intent.putExtra(ProductDetailFragment.PARAM_PRODUCT_DETAIL, result);
        startActivity(intent);
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
        rvCategoryList.setVisibility(View.GONE);
        tvSort.setVisibility(View.GONE);
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        llLoading.setVisibility(View.GONE);
        rvCategoryList.setVisibility(View.VISIBLE);
        tvSort.setVisibility(View.VISIBLE);
    }

    @Override
    public void sortProduct(int criteria)
    {
        showLoading();

        switch (criteria)
        {
            case R.id.tv_most_ordered:
                presenter.sortByMostOrdered();
                break;
            case R.id.tv_most_shared:
                presenter.sortByMostShared();
                break;
            case R.id.tv_most_viewed:
                presenter.sortByMostViewed();
                break;
        }

    }

    @Override
    public List<ProductDetails> getProductList()
    {
        return productListAdapter.getList();
    }

    @OnClick(R.id.tv_sort)
    public void showSortBottomSheet()
    {
        SortBottomSheetDialogFragment bottomSheetFragment = new SortBottomSheetDialogFragment();
        bottomSheetFragment.setTargetFragment(this, 0);
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenter.onDetach();
    }
}
