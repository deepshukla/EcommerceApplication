package com.lex.ecommerceapplication.domain.categorylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lex.ecommerceapplication.BaseActivity;
import com.lex.ecommerceapplication.BaseApplication;
import com.lex.ecommerceapplication.BaseFragment;
import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.domain.cartdetails.CartActivity;
import com.lex.ecommerceapplication.domain.categorylist.adapter.CategoryGroup;
import com.lex.ecommerceapplication.domain.categorylist.adapter.SubCategoryListAdapter;
import com.lex.ecommerceapplication.domain.productlist.ProductListActivity;
import com.lex.ecommerceapplication.domain.productlist.ProductListFragment;
import com.lex.ecommerceapplication.model.response.CategoryDTO;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Subcategory list fragment.
 *
 * @author DeepS
 */
public class SubCategoryListFragment extends BaseFragment implements Contracts.View
{

    @BindView(R.id.rv_movie_list)
    RecyclerView rvCategoryList;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_info)
    AppCompatTextView tvInfo;

    Unbinder unbinder;

    @Inject
    CategoryListPresenter presenter;

    private LinearLayoutManager linearLayoutManager;

    SubCategoryListAdapter categoryAdapter;

    public static final String STR_CATEGORY = "str_category";

    private CategoryDTO categoryDTO;

    public static SubCategoryListFragment newInstance(CategoryDTO categoryDTO)
    {
        Bundle args = new Bundle();
        args.putParcelable(STR_CATEGORY, categoryDTO);
        SubCategoryListFragment fragment = new SubCategoryListFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent()
                .newCategoryComponent(new CategoryModule(this))
                .inject(this);
        categoryDTO = getArguments().getParcelable(STR_CATEGORY);

        if (((BaseActivity) getActivity()).getSupportActionBar() != null)
            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_sub_category_list, container, false);
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
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rvCategoryList.setLayoutManager(linearLayoutManager);
        presenter.fetchSubCategoryFromDatabase(categoryDTO.getId());
    }

    @Override
    public void populateSubCategories(List<CategoryGroup> categoryGroupList)
    {
        if (categoryGroupList != null && categoryGroupList.size() > 0)
        {
            categoryAdapter = new SubCategoryListAdapter(categoryGroupList, getActivity(), this);
            rvCategoryList.setAdapter(categoryAdapter);
            hideLoading();
        }
    }

    @Override
    public void populateData(List<CategoryDTO> categoryGroupList)
    {
    }

    @Override
    public void onCategoryItemSelected(CategoryDTO categoryDTO)
    {
        Intent intent = new Intent(getActivity(), ProductListActivity.class);
        intent.putExtra(ProductListFragment.STR_CATEGORY, categoryDTO);
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
        progressBar.setVisibility(View.VISIBLE);
        tvInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        progressBar.setVisibility(View.GONE);
        tvInfo.setVisibility(View.GONE);
        rvCategoryList.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenter.onDetach();
    }

}
