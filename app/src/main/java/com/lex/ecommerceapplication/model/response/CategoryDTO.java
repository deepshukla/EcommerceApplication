package com.lex.ecommerceapplication.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Category DTO
 *
 * @author DeepS
 */
public class CategoryDTO implements Parcelable
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("products")
    @Expose
    private List<ProductDTO> products = null;

    @SerializedName("child_categories")
    @Expose
    private List<Integer> childCategories = null;


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<ProductDTO> getProducts()
    {
        return products;
    }

    public void setProducts(List<ProductDTO> products)
    {
        this.products = products;
    }

    public List<Integer> getChildCategories()
    {
        return childCategories;
    }

    public void setChildCategories(List<Integer> childCategories)
    {
        this.childCategories = childCategories;
    }

    public CategoryDTO()
    {
    }

    public CategoryDTO(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    protected CategoryDTO(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
    }

    public static final Creator<CategoryDTO> CREATOR = new Creator<CategoryDTO>()
    {
        @Override
        public CategoryDTO createFromParcel(Parcel in)
        {
            return new CategoryDTO(in);
        }

        @Override
        public CategoryDTO[] newArray(int size)
        {
            return new CategoryDTO[size];
        }
    };

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
    }
}