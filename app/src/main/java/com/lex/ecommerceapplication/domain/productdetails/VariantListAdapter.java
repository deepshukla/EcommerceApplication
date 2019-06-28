package com.lex.ecommerceapplication.domain.productdetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.model.response.VariantDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Category list adapter
 *
 * @author DeepS
 */
public class VariantListAdapter extends RecyclerView.Adapter<VariantListAdapter.VariantViewHolder>
{
    private Context context;

    private List<VariantDTO> variantDTOList;
    private LayoutInflater layoutInflater;
    private Contracts.View productDetailView;
    private View.OnClickListener onClickListener;

    public VariantListAdapter(View.OnClickListener onClickListener, Context context, List<VariantDTO> results, Contracts.View productDetailView)
    {
        this.context = context;
        variantDTOList = results;
        layoutInflater = LayoutInflater.from(context);
        this.productDetailView = productDetailView;
        this.onClickListener = onClickListener;
    }

    public void addAll(List<VariantDTO> results)
    {
        variantDTOList.addAll(results);
        notifyDataSetChanged();
    }

    public List<VariantDTO> getList()
    {
        return variantDTOList;
    }

    public void clear()
    {
        if (variantDTOList != null)
        {
            variantDTOList.clear();
        }
    }

    @Override
    public VariantViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new VariantViewHolder(layoutInflater.inflate(R.layout.item_variant, parent,
                false));
    }

    @Override
    public void onBindViewHolder(VariantViewHolder holder, int position)
    {
        holder.bindViews(variantDTOList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return variantDTOList.size();
    }

    class VariantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.tv_color)
        TextView tvColor;
        @BindView(R.id.tv_size)
        TextView tvSize;
        @BindView(R.id.tv_price)
        TextView tvPrice;

        VariantViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindViews(VariantDTO result)
        {
            tvPrice.setText(context.getString(R.string.str_rs, result.getPrice()));
            if (result.getColor() != null)
            {
                tvColor.setVisibility(View.VISIBLE);
                tvColor.setText(context.getString(R.string.str_color, result.getColor()));
            } else
                tvColor.setVisibility(View.GONE);

            if (result.getSize() != 0)
            {
                tvSize.setVisibility(View.VISIBLE);
                tvSize.setText(context.getString(R.string.str_size, result.getSize()));
            } else
                tvSize.setVisibility(View.GONE);

        }

        @Override
        public void onClick(View v)
        {
            productDetailView.onVariantSelected(variantDTOList.get(getAdapterPosition()));
            onClickListener.onClick(v);
        }
    }
}
