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
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.lex.ecommerceapplication.BaseActivity;
import com.lex.ecommerceapplication.BaseApplication;
import com.lex.ecommerceapplication.BaseFragment;
import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.api.ApiService;
import com.lex.ecommerceapplication.domain.cartdetails.CartActivity;
import com.lex.ecommerceapplication.domain.categorylist.adapter.CategoryGroup;
import com.lex.ecommerceapplication.domain.categorylist.adapter.CategoryListAdapter;
import com.lex.ecommerceapplication.model.response.CategoryDTO;
import com.lex.ecommerceapplication.util.ActivityUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Category list fragment.
 *
 * @author DeepS
 */
public class CategoryListFragment extends BaseFragment implements Contracts.View
{

    @BindView(R.id.rv_movie_list)
    RecyclerView rvCategoryList;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_info)
    AppCompatTextView tvInfo;
    @BindView(R.id.iv_image)
    ImageView ivImage;
    Unbinder unbinder;

    @Inject
    CategoryListPresenter presenter;

    private LinearLayoutManager linearLayoutManager;

    CategoryListAdapter categoryAdapter;

    @Inject
    ApiService apiService;

    public static CategoryListFragment newInstance()
    {
        Bundle args = new Bundle();
        CategoryListFragment fragment = new CategoryListFragment();
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);
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
    public void onResume()
    {
        super.onResume();

        if (((BaseActivity) getActivity()).getSupportActionBar() != null)
            ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
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
        //categoryAdapter = new categoryAdapter(getContext(), this);
        //rvCategoryList.setAdapter(categoryListAdapter);
        presenter.fetchCategoryAndProductData();
    }

    @Override
    public void populateData(List<CategoryDTO> categoryGroupList)
    {
        if (categoryGroupList != null && categoryGroupList.size() > 0)
        {
            categoryAdapter = new CategoryListAdapter(getActivity(), categoryGroupList, this);
            rvCategoryList.setAdapter(categoryAdapter);
            hideLoading();
        }
    }


    @Override
    public void onCategoryItemSelected(CategoryDTO categoryDTO)
    {
        ActivityUtil.replaceFragment((BaseActivity) getActivity(), this, SubCategoryListFragment.newInstance(categoryDTO), true, R.id.content_frame);
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
        ivImage.setVisibility(View.GONE);
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
        ivImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void populateSubCategories(List<CategoryGroup> categoryGroups)
    {
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenter.onDetach();
    }
}
