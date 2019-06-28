package com.lex.ecommerceapplication.domain.productdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lex.ecommerceapplication.BaseApplication;
import com.lex.ecommerceapplication.BaseFragment;
import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.domain.cartdetails.CartActivity;
import com.lex.ecommerceapplication.model.response.VariantDTO;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Product details fragment.
 *
 * @author DeepS
 */
public class ProductDetailFragment extends BaseFragment implements Contracts.View
{
    @BindView(R.id.rl_details)
    RelativeLayout rlDetails;

    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.tv_info)
    AppCompatTextView tvInfo;

    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_color)
    TextView tvColor;
    @BindView(R.id.tv_size)
    TextView tvSize;
    @BindView(R.id.tv_promote)
    TextView tvPromote;

    @BindView(R.id.btn_add_to_cart)
    Button btnAddToCart;
    Unbinder unbinder;

    @Inject
    ProductDetailPresenter presenter;

    public static final String PARAM_PRODUCT_DETAIL = "param_product_detail";

    private ProductDetails productDetails;
    private ArrayList<VariantDTO> variants;

    public static ProductDetailFragment newInstance(ProductDetails productDetails)
    {
        Bundle args = new Bundle();
        args.putParcelable(PARAM_PRODUCT_DETAIL, productDetails);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent()
                .newProductDetailComponent(new ProductDetailModule(this))
                .inject(this);

        productDetails = getArguments().getParcelable(PARAM_PRODUCT_DETAIL);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_product_detail, container, false);
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
                goToCart();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void goToCart()
    {
        Intent intent = new Intent(getActivity(), CartActivity.class);
        startActivity(intent);
    }

    //View methods
    @Override
    public void initView()
    {

        btnAddToCart.setText(R.string.add_to_cart);
        btnAddToCart.setTag(null);

        tvProductName.setText(productDetails.getProductName());
        tvPrice.setText(getString(R.string.str_rs, productDetails.getPrice()));

        if (productDetails.getColor() != null)
            tvColor.setText(getString(R.string.str_color, productDetails.getColor()));

        if (productDetails.getSize() != 0)
            tvSize.setText(getString(R.string.str_size, productDetails.getSize()));

        tvPromote.setText("");
        if (productDetails.getMostOrdered() != 0)
            tvPromote.append(getString(R.string.str_ordered, productDetails.getMostOrdered()));
        else if (productDetails.getMostShared() != 0)
            tvPromote.append(getString(R.string.str_shared, productDetails.getMostShared()));
        else if (productDetails.getMostViewed() != 0)
            tvPromote.append(getString(R.string.str_viewed, productDetails.getMostViewed()));

        presenter.getProductVariant(productDetails.getId());
    }

    @Override
    public void onError(Throwable throwable)
    {
        hideLoading();
        Snackbar.make(tvInfo, R.string.something_went_wrong, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAddError(Throwable throwable)
    {
        hideLoading();
        Snackbar.make(tvInfo, R.string.product_already_in_cart, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading()
    {
        rlDetails.setVisibility(View.GONE);
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading()
    {
        llLoading.setVisibility(View.GONE);
        rlDetails.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.btn_add_to_cart)
    public void addToCart()
    {
        if (btnAddToCart.getTag() != null && (boolean) btnAddToCart.getTag())
        {
            goToCart();
        } else
            presenter.addtoCart(productDetails);
    }

    @OnClick(R.id.btn_select_variant)
    public void ViewSimilar()
    {
        VariantBottomSheetDialogFragment bottomSheetFragment = VariantBottomSheetDialogFragment.newInstance(variants);
        bottomSheetFragment.setTargetFragment(this, 0);
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onSuccess()
    {
        btnAddToCart.setText(R.string.go_to_cart);
        btnAddToCart.setTag(true);
        Snackbar.make(tvInfo, R.string.product_added, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onVariantSelected(VariantDTO variantDTO)
    {
        productDetails.setColor(variantDTO.getColor());
        productDetails.setSize(variantDTO.getSize());
        productDetails.setVariantId(variantDTO.getId());
        productDetails.setPrice(variantDTO.getPrice());
        initView();
    }

    @Override
    public void onVariantFetched(ArrayList<VariantDTO> variants)
    {
        this.variants = variants;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        presenter.onDetach();
    }
}
