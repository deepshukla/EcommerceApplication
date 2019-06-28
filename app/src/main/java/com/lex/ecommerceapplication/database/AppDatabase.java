package com.lex.ecommerceapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.lex.ecommerceapplication.dao.CartDao;
import com.lex.ecommerceapplication.dao.CategoryDao;
import com.lex.ecommerceapplication.dao.ParentChildCategoryMappingDao;
import com.lex.ecommerceapplication.dao.ProductDao;
import com.lex.ecommerceapplication.dao.ProductRankingDao;
import com.lex.ecommerceapplication.dao.ProductTaxDao;
import com.lex.ecommerceapplication.dao.TaxDao;
import com.lex.ecommerceapplication.dao.VariantDao;
import com.lex.ecommerceapplication.model.roomentities.Cart;
import com.lex.ecommerceapplication.model.roomentities.Category;
import com.lex.ecommerceapplication.model.roomentities.ParentChildCategoryMapping;
import com.lex.ecommerceapplication.model.roomentities.Product;
import com.lex.ecommerceapplication.model.roomentities.ProductRanking;
import com.lex.ecommerceapplication.model.roomentities.ProductTax;
import com.lex.ecommerceapplication.model.roomentities.Tax;
import com.lex.ecommerceapplication.model.roomentities.Variant;

/**
 * Room database for storing api response data.
 *
 * @author DeepS
 */
@Database(entities = {Category.class, Product.class, Tax.class, Variant.class, ProductTax.class,
        ProductRanking.class, ParentChildCategoryMapping.class, Cart.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract CategoryDao categoryDataDao();

    public abstract ProductDao productDataDao();

    public abstract TaxDao taxDataDao();

    public abstract VariantDao variantDataDao();

    public abstract ProductTaxDao productTaxDataDao();

    public abstract ProductRankingDao productRankingDataDao();

    public abstract ParentChildCategoryMappingDao parentChildCategoryMappingDataDao();

    public abstract CartDao cartDao();
}
