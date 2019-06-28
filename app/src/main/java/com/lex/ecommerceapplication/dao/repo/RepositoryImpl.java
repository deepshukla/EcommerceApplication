package com.lex.ecommerceapplication.dao.repo;

import javax.inject.Inject;

/**
 * Repository implementation
 *
 * @author DeepS
 */
public class RepositoryImpl implements Repository
{
    LocalCategoryData localCategoryData;
    LocalParentChildCategoryMappingData localParentChildCategoryMappingData;
    LocalProductData localProductData;
    LocalProductRankingData localProductRankingData;
    LocalProductTaxData localProductTaxData;
    LocalTaxData localTaxData;
    LocalVariantData localVariantData;
    LocalCartData localCartData;

    @Inject
    public RepositoryImpl(LocalCategoryData localCategoryData, LocalParentChildCategoryMappingData localParentChildCategoryMappingData, LocalProductData localProductData, LocalProductRankingData localProductRankingData, LocalProductTaxData localProductTaxData, LocalTaxData localTaxData, LocalVariantData localVariantData, LocalCartData localCartData)
    {
        this.localCategoryData = localCategoryData;
        this.localParentChildCategoryMappingData = localParentChildCategoryMappingData;
        this.localProductData = localProductData;
        this.localProductRankingData = localProductRankingData;
        this.localProductTaxData = localProductTaxData;
        this.localTaxData = localTaxData;
        this.localVariantData = localVariantData;
        this.localCartData = localCartData;
    }

    @Override
    public LocalCategoryData categoryData()
    {
        return localCategoryData;
    }

    @Override
    public LocalParentChildCategoryMappingData parentChildCategoryMappingData()
    {
        return localParentChildCategoryMappingData;
    }

    @Override
    public LocalProductData productData()
    {
        return localProductData;
    }

    @Override
    public LocalProductRankingData productRankingData()
    {
        return localProductRankingData;
    }

    @Override
    public LocalProductTaxData productTaxData()
    {
        return localProductTaxData;
    }

    @Override
    public LocalTaxData taxData()
    {
        return localTaxData;
    }

    @Override
    public LocalVariantData variantData()
    {
        return localVariantData;
    }

    @Override
    public LocalCartData cardData()
    {
        return localCartData;
    }
}
