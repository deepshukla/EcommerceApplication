package com.lex.ecommerceapplication.domain.categorylist.adapter;

import com.lex.ecommerceapplication.model.response.CategoryDTO;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Category group
 *
 * @author DeepS
 */
public class CategoryGroup extends ExpandableGroup<CategoryDTO>
{
    private CategoryDTO parentCategory;

    public CategoryGroup(CategoryDTO categoryDTO, List<CategoryDTO> items)
    {
        super(categoryDTO.getName(), items);
        parentCategory = categoryDTO;
    }

    public CategoryDTO getParentCategory()
    {
        return parentCategory;
    }
}