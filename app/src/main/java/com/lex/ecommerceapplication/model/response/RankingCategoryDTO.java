package com.lex.ecommerceapplication.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Ranking category DTO
 *
 * @author DeepS
 */
public class RankingCategoryDTO
{
    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private List<ProductRankingDTO> productRankingDTOList = null;


    public String getRanking()
    {
        return ranking;
    }

    public void setRanking(String ranking)
    {
        this.ranking = ranking;
    }

    public List<ProductRankingDTO> getProductRankingDTOList()
    {
        return productRankingDTOList;
    }

    public void setProductRankingDTOList(List<ProductRankingDTO> productRankingDTOList)
    {
        this.productRankingDTOList = productRankingDTOList;
    }
}