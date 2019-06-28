package com.lex.ecommerceapplication.model.roomentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Product Ranking entity.
 *
 * @author DeepS
 */
@ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "p_id")
@Entity(tableName = "ProductRanking")
public class ProductRanking
{
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "p_id")
    private int id;
    @ColumnInfo(name = "most_viewed")
    private int mostViewed;
    @ColumnInfo(name = "most_ordered")
    private int mostOrdered;
    @ColumnInfo(name = "most_shared")
    private int mostShared;

    public ProductRanking()
    {
    }

    public ProductRanking(@NotNull int id, int mostViewed, int mostOrdered, int mostShared)
    {
        this.id = id;
        this.mostViewed = mostViewed;
        this.mostOrdered = mostOrdered;
        this.mostShared = mostShared;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getMostViewed()
    {
        return mostViewed;
    }

    public void setMostViewed(int mostViewed)
    {
        this.mostViewed = mostViewed;
    }

    public int getMostOrdered()
    {
        return mostOrdered;
    }

    public void setMostOrdered(int mostOrdered)
    {
        this.mostOrdered = mostOrdered;
    }

    public int getMostShared()
    {
        return mostShared;
    }

    public void setMostShared(int mostShared)
    {
        this.mostShared = mostShared;
    }
}