package com.lex.ecommerceapplication.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;

/**
 * @author DeepS
 */
public class LoadingDialog extends DialogFragment
{

    public static final String TITLE = "title";

    public static LoadingDialog newInstance(String title)
    {
        final LoadingDialog loadingDialog = new LoadingDialog();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        loadingDialog.setArguments(bundle);
        return loadingDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.loading_dialog, container, true);

        TextView tvTitleDesc = view.findViewById(R.id.tv_step_name);
        tvTitleDesc.setText(getArguments().getString(TITLE));
        setCancelable(false);

        return view;
    }
}