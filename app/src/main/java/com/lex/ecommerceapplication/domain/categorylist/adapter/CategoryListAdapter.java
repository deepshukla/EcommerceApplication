package com.lex.ecommerceapplication.domain.categorylist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.domain.categorylist.Contracts;
import com.lex.ecommerceapplication.model.response.CategoryDTO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Category list adapter
 *
 * @author DeepS
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>
{
    private Context context;

    private List<CategoryDTO> productDetailsList;
    private LayoutInflater layoutInflater;
    private Contracts.View categoryView;

    public CategoryListAdapter(Context context, List<CategoryDTO> results, Contracts.View categoryView)
    {
        this.context = context;
        productDetailsList = results;
        layoutInflater = LayoutInflater.from(context);
        this.categoryView = categoryView;
    }

    public void addAll(List<CategoryDTO> results)
    {
        productDetailsList.addAll(results);
        notifyDataSetChanged();
    }

    public List<CategoryDTO> getList()
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
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new CategoryViewHolder(layoutInflater.inflate(R.layout.item_category, parent,
                false));
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position)
    {
        holder.bindViews(productDetailsList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return productDetailsList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        @BindView(R.id.tv_category_name)
        TextView tvProductName;

        CategoryViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void bindViews(CategoryDTO result)
        {
            tvProductName.setText(result.getName());
        }

        @Override
        public void onClick(View v)
        {
            categoryView.onCategoryItemSelected(productDetailsList.get(getAdapterPosition()));
        }
    }
}
