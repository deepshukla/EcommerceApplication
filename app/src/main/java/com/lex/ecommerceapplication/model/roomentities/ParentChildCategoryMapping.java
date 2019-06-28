package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * ParentChildCategoryMapping entity.
 *
 * @author DeepS
 */
@Entity(tableName = "ParentChildCategoryMapping")
public class ParentChildCategoryMapping
{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "child_id")
    private int childId;
    @ColumnInfo(name = "parent_id")
    private int parentId;

    public ParentChildCategoryMapping()
    {
    }

    public ParentChildCategoryMapping(int childId, int parentId)
    {
        this.childId = childId;
        this.parentId = parentId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getChildId()
    {
        return childId;
    }

    public void setChildId(int childId)
    {
        this.childId = childId;
    }

    public int getParentId()
    {
        return parentId;
    }

    public void setParentId(int parentId)
    {
        this.parentId = parentId;
    }
}