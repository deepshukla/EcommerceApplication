package com.lex.ecommerceapplication.model.roomentities.relation;

import android.arch.persistence.room.ColumnInfo;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Product details
 *
 * @author DeepS
 */
public class ProductDetails implements Parcelable
{
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "product_name")
    private String productName;
    @ColumnInfo(name = "category_id")
    private int categoryId;
    @ColumnInfo(name = "variant_id")
    private int variantId;
    @ColumnInfo(name = "color")
    private String color;
    @ColumnInfo(name = "size")
    private int size;
    @ColumnInfo(name = "price")
    private int price;
    @ColumnInfo(name = "most_viewed")
    private int mostViewed;
    @ColumnInfo(name = "most_ordered")
    private int mostOrdered;
    @ColumnInfo(name = "most_shared")
    private int mostShared;

    public ProductDetails()
    {
    }

    protected ProductDetails(Parcel in)
    {
        id = in.readInt();
        productName = in.readString();
        categoryId = in.readInt();
        variantId = in.readInt();
        color = in.readString();
        size = in.readInt();
        price = in.readInt();
        mostViewed = in.readInt();
        mostOrdered = in.readInt();
        mostShared = in.readInt();
    }

    public static final Creator<ProductDetails> CREATOR = new Creator<ProductDetails>()
    {
        @Override
        public ProductDetails createFromParcel(Parcel in)
        {
            return new ProductDetails(in);
        }

        @Override
        public ProductDetails[] newArray(int size)
        {
            return new ProductDetails[size];
        }
    };

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public int getVariantId()
    {
        return variantId;
    }

    public void setVariantId(int variantId)
    {
        this.variantId = variantId;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(productName);
        dest.writeInt(categoryId);
        dest.writeInt(variantId);
        dest.writeString(color);
        dest.writeInt(size);
        dest.writeInt(price);
        dest.writeInt(mostViewed);
        dest.writeInt(mostOrdered);
        dest.writeInt(mostShared);
    }


    public static class MostOrderedComparator implements Comparator<ProductDetails>
    {
        @Override
        public int compare(ProductDetails productDetails1, ProductDetails productDetails2)
        {
            return Integer.compare(productDetails2.getMostOrdered(), productDetails1.getMostOrdered());
        }
    }

    public static class MostSharedComparator implements Comparator<ProductDetails>
    {
        @Override
        public int compare(ProductDetails productDetails1, ProductDetails productDetails2)
        {
            return Integer.compare(productDetails2.getMostShared(), productDetails1.getMostShared());
        }
    }

    public static class MostViewedComparator implements Comparator<ProductDetails>
    {
        @Override
        public int compare(ProductDetails productDetails1, ProductDetails productDetails2)
        {
            return Integer.compare(productDetails2.getMostViewed(), productDetails1.getMostViewed());
        }
    }
}
