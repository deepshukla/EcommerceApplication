package com.lex.ecommerceapplication.domain.categorylist.adapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.lex.ecommerceapplication.R;
import com.lex.ecommerceapplication.model.response.CategoryDTO;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Parent category view holder.
 *
 * @author DeepS
 */
public class ParentCategoryViewHolder extends GroupViewHolder
{
    @BindView(R.id.tv_category_name)
    TextView categoryName;

    @BindView(R.id.iv_arrow)
    ImageView ivArrow;

    public ParentCategoryViewHolder(View itemView)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void onBind(CategoryDTO categoryDTO)
    {
        categoryName.setText(categoryDTO.getName());
    }

    @Override
    public void expand()
    {
        animateExpand();
    }

    @Override
    public void collapse()
    {
        animateCollapse();
    }

    private void animateExpand()
    {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        ivArrow.setAnimation(rotate);
    }

    private void animateCollapse()
    {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        ivArrow.setAnimation(rotate);
    }
}