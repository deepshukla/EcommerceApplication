package com.lex.ecommerceapplication.domain.productdetails;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.model.response.VariantDTO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Sort bottom sheet
 *
 * @author DeepS
 */
public class VariantBottomSheetDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener
{

    @BindView(R.id.rv_variant)
    RecyclerView rvVariant;

    Contracts.View productDetailView;

    public static final String ARG_VARIANT = "arg_variant";

    private ArrayList<VariantDTO> variantDTOArrayList;

    public static VariantBottomSheetDialogFragment newInstance(ArrayList<VariantDTO> variants)
    {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_VARIANT, variants);
        VariantBottomSheetDialogFragment fragment = new VariantBottomSheetDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        variantDTOArrayList = getArguments().getParcelableArrayList(ARG_VARIANT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_variant_bottom_sheet, container, false);
        ButterKnife.bind(this, view);
        productDetailView = (Contracts.View) getTargetFragment();
        initView();
        return view;

    }

    void initView()
    {
        VariantListAdapter variantListAdapter = new VariantListAdapter(this, getContext(), variantDTOArrayList, productDetailView);
        rvVariant.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        rvVariant.setAdapter(variantListAdapter);
    }

    @Override
    public void onClick(View v)
    {
        getDialog().dismiss();
    }
}