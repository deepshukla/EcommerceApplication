package com.lex.ecommerceapplication.domain.productlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.model.roomentities.relation.ProductDetails;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Product list adapter
 *
 * @author DeepS
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>
{
    private Context context;
    private List<ProductDetails> productDetailsList;
    private LayoutInflater layoutInflater;
    private Contracts.View categoryView;

    public ProductListAdapter(Context context, Contracts.View categoryView)
    {
        this.context = context;
        productDetailsList = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
        this.categoryView = categoryView;
    }

    public void addAll(List<ProductDetails> results)
    {
        productDetailsList.addAll(results);
        notifyDataSetChanged();
    }

    public void setAll(List<ProductDetails> productDetailsList)
    {
        this.productDetailsList = productDetailsList;
        notifyDataSetChanged();
    }

    public List<ProductDetails> getList()
    {
        return productDetailsList;
    }

    public void clear()
    {
        if (productDetailsList != null)
        {
            productDetailsList.clear();
        }
    }

    @Override
    public ProductListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ProductListViewHolder(layoutInflater.inflate(R.layout.item_product, parent,
                false));
    }

    @Override
    public void onBindViewHolder(ProductListViewHolder holder, int position)
    {
        holder.bindViews(productDetailsList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return productDetailsList.size();
    }

    class ProductListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.tv_product_name)
        TextView tvProductName;
        @BindView(R.id.tv_product_price)
        TextView tvProductPrice;
        @BindView(R.id.tv_product_size)
        TextView tvProductSize;
        @BindView(R.id.tv_product_color)
        TextView tvProductColor;

        ProductListViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindViews(ProductDetails result)
        {
            tvProductName.setText(result.getProductName());
            tvProductPrice.setText(context.getString(R.string.str_rs, result.getPrice()));

            if (result.getSize() != 0)
            {
                tvProductSize.setVisibility(View.VISIBLE);
                tvProductSize.setText(context.getString(R.string.str_size, result.getSize()));
            } else
                tvProductSize.setVisibility(View.GONE);

            if (result.getColor() != null)
            {
                tvProductColor.setVisibility(View.VISIBLE);
                tvProductColor.setText(context.getString(R.string.str_color, result.getColor()));
            } else
            {
                tvProductColor.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v)
        {
            categoryView.onCategoryItemSelected(productDetailsList.get(getAdapterPosition()));
        }
    }
}
