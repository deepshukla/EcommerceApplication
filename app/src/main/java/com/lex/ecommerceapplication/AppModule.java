package com.lex.ecommerceapplication;

import android.arch.persistence.room.Room;

import com.lex.ecommerceapplication.dao.CategoryDao;
import com.lex.ecommerceapplication.dao.ParentChildCategoryMappingDao;
import com.lex.ecommerceapplication.dao.ProductDao;
import com.lex.ecommerceapplication.dao.ProductRankingDao;
import com.lex.ecommerceapplication.dao.ProductTaxDao;
import com.lex.ecommerceapplication.dao.TaxDao;
import com.lex.ecommerceapplication.dao.VariantDao;
import com.lex.ecommerceapplication.dao.repo.LocalCartData;
import com.lex.ecommerceapplication.dao.repo.LocalCategoryData;
import com.lex.ecommerceapplication.dao.repo.LocalParentChildCategoryMappingData;
import com.lex.ecommerceapplication.dao.repo.LocalProductData;
import com.lex.ecommerceapplication.dao.repo.LocalProductRankingData;
import com.lex.ecommerceapplication.dao.repo.LocalProductTaxData;
import com.lex.ecommerceapplication.dao.repo.LocalTaxData;
import com.lex.ecommerceapplication.dao.repo.LocalVariantData;
import com.lex.ecommerceapplication.dao.repo.Repository;
import com.lex.ecommerceapplication.dao.repo.RepositoryImpl;
import com.lex.ecommerceapplication.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DeepS
 * Provides application class
 */
@Module
public class AppModule
{
    private final BaseApplication application;

    public AppModule(BaseApplication application)
    {
        this.application = application;
    }

    @Singleton
    @Provides
    Repository provideDatabaseRepository(LocalCategoryData localCategoryData,
                                         LocalParentChildCategoryMappingData localParentChildCategoryMappingData,
                                         LocalProductData localProductData,
                                         LocalProductRankingData localProductRankingData,
                                         LocalProductTaxData localProductTaxData,
                                         LocalTaxData localTaxData,
                                         LocalVariantData localVariantData,
                                         LocalCartData localCartData)
    {
        return new RepositoryImpl(localCategoryData, localParentChildCategoryMappingData,
                localProductData, localProductRankingData, localProductTaxData, localTaxData, localVariantData, localCartData);
    }

    @Singleton
    @Provides
    LocalCategoryData providesLocalCategoryDao(AppDatabase appDatabase)
    {
        return new LocalCategoryData(appDatabase);
    }

    @Singleton
    @Provides
    LocalParentChildCategoryMappingData providesLocalParentChildCategoryMappingCategoryDao(AppDatabase appDatabase)
    {
        return new LocalParentChildCategoryMappingData(appDatabase);
    }

    @Singleton
    @Provides
    LocalProductData providesLocalProductDao(AppDatabase appDatabase)
    {
        return new LocalProductData(appDatabase);
    }

    @Singleton
    @Provides
    LocalProductRankingData providesLocalProductRankingDao(AppDatabase appDatabase)
    {
        return new LocalProductRankingData(appDatabase);
    }

    @Singleton
    @Provides
    LocalProductTaxData providesLocalProductTaxDao(AppDatabase appDatabase)
    {
        return new LocalProductTaxData(appDatabase);
    }

    @Singleton
    @Provides
    LocalTaxData providesLocalTaxDao(AppDatabase appDatabase)
    {
        return new LocalTaxData(appDatabase);
    }

    @Singleton
    @Provides
    LocalVariantData providesLocalVariantDao(AppDatabase appDatabase)
    {
        return new LocalVariantData(appDatabase);
    }

    @Singleton
    @Provides
    LocalCartData providesLocalCartDao(AppDatabase appDatabase)
    {
        return new LocalCartData(appDatabase);
    }

    @Singleton
    @Provides
    CategoryDao providesCategoryDao(AppDatabase appDatabase)
    {
        return appDatabase.categoryDataDao();
    }

    @Provides
    @Singleton
    BaseApplication providesApplication()
    {
        return application;
    }

    @Provides
    @Singleton
    AppDatabase providesAppDatabase()
    {
        return Room.databaseBuilder(application, AppDatabase.class, "eComData").build();
    }
}
