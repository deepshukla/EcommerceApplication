package com.lex.ecommerceapplication.domain.productlist;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Sort bottom sheet
 *
 * @author DeepS
 */
public class SortBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener
{

    @BindView(R.id.tv_most_viewed)
    TextView tvMostViewed;
    @BindView(R.id.tv_most_ordered)
    TextView tvMostOrdered;
    @BindView(R.id.tv_most_shared)
    TextView tvMostShared;

    Contracts.View productListView;

    public SortBottomSheetDialogFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_sort_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        productListView = (Contracts.View) getTargetFragment();
        initView();
        return view;

    }

    void initView()
    {
        tvMostOrdered.setOnClickListener(this);
        tvMostShared.setOnClickListener(this);
        tvMostViewed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        productListView.sortProduct(v.getId());
        getDialog().dismiss();
    }
}