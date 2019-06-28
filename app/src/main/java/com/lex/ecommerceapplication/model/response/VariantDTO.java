package com.lex.ecommerceapplication.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Variant DTO
 *
 * @author DeepS
 */
public class VariantDTO implements Parcelable
{
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private int size;
    @SerializedName("price")
    @Expose
    private int price;

    public VariantDTO(int id, String color, int size, int price)
    {
        this.id = id;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    protected VariantDTO(Parcel in)
    {
        id = in.readInt();
        color = in.readString();
        size = in.readInt();
        price = in.readInt();
    }

    public static final Creator<VariantDTO> CREATOR = new Creator<VariantDTO>()
    {
        @Override
        public VariantDTO createFromParcel(Parcel in)
        {
            return new VariantDTO(in);
        }

        @Override
        public VariantDTO[] newArray(int size)
        {
            return new VariantDTO[size];
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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(color);
        dest.writeInt(size);
        dest.writeInt(price);
    }
}