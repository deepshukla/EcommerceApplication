package com.lex.ecommerceapplication.domain.categorylist;

import android.util.Log;

import com.lex.ecommerceapplication.api.ApiService;
import com.lex.ecommerceapplication.dao.repo.Repository;
import com.lex.ecommerceapplication.domain.categorylist.adapter.CategoryGroup;
import com.lex.ecommerceapplication.model.response.CategoryDTO;
import com.lex.ecommerceapplication.model.response.MasterdataDTO;
import com.lex.ecommerceapplication.model.response.ProductDTO;
import com.lex.ecommerceapplication.model.response.ProductRankingDTO;
import com.lex.ecommerceapplication.model.response.RankingCategoryDTO;
import com.lex.ecommerceapplication.model.response.TaxDTO;
import com.lex.ecommerceapplication.model.response.VariantDTO;
import com.lex.ecommerceapplication.model.roomentities.Category;
import com.lex.ecommerceapplication.model.roomentities.ParentChildCategoryMapping;
import com.lex.ecommerceapplication.model.roomentities.Product;
import com.lex.ecommerceapplication.model.roomentities.ProductRanking;
import com.lex.ecommerceapplication.model.roomentities.ProductTax;
import com.lex.ecommerceapplication.model.roomentities.Tax;
import com.lex.ecommerceapplication.model.roomentities.Variant;
import com.lex.ecommerceapplication.model.roomentities.relation.CategoryAndMapping;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * category list presenter.
 *
 * @author DeepS
 */
public class CategoryListPresenter implements Contracts.Presenter
{
    private Contracts.View categoryView;
    private ApiService apiService;
    private Repository repository;
    private List<Category> parentCategoryList;
    private List<CategoryAndMapping> childCategoryList;
    private int parentCategoryId;
    private CompositeDisposable compositeDisposable;

    public CategoryListPresenter(Contracts.View categoryView, ApiService apiService, Repository repository)
    {
        super();
        this.categoryView = categoryView;
        this.apiService = apiService;
        this.repository = repository;
        compositeDisposable = new CompositeDisposable();

    }

    @Override
    public void init()
    {
        categoryView.initView();
    }

    @Override
    public void fetchCategoryAndProductData()
    {
        showLoading();

        Disposable disposable= repository.categoryData().getCategories().
                subscribeOn(Schedulers.io())
                .map(categoryList ->
                        categoryList != null && categoryList.size() != 0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fetchData, categoryView::onError);
        compositeDisposable.add(disposable);
    }

    private void fetchData(boolean isFetched)
    {
        if (!isFetched)
            fetchDataFromServer();
        else
            fetchFromDatabase();

    }

    private void fetchFromDatabase()
    {
        Disposable disposable= repository.categoryData().getParentCategories().
                subscribeOn(Schedulers.io())
                .map(categoryList -> {

                    List<CategoryDTO> categoryDTOS = new ArrayList<>();
                    for (Category category : categoryList)
                    {
                        categoryDTOS.add(new CategoryDTO(category.getCategoryId(), category.getCategoryName()));
                    }
                    return categoryDTOS;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryView::populateData, categoryView::onError);

        compositeDisposable.add(disposable);

    }

    private void fetchDataFromServer()
    {
       Disposable disposable= apiService.getCategoryAndProductDetails().subscribeOn(Schedulers.io())
                .map(masterdataDTO -> {
                    saveData(masterdataDTO);
                    return masterdataDTO.getCategories();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::dataFetched, categoryView::onError);

        compositeDisposable.add(disposable);

    }

    private void dataFetched(List<CategoryDTO> categoryDTOS)
    {
        fetchFromDatabase();
    }


    private void saveData(MasterdataDTO masterdataDTO)
    {
        Log.d("TAG", "saveData: ");

        try
        {
            for (CategoryDTO categoryDTO : masterdataDTO.getCategories())
            {
                Category category = new Category(categoryDTO.getId(), categoryDTO.getName());
                repository.categoryData().addItem(category);

                if (categoryDTO.getProducts() != null && categoryDTO.getProducts().size() != 0)
                {

                    for (ProductDTO productDTO : categoryDTO.getProducts())
                    {
                        Product product = new Product(productDTO.getId(), productDTO.getName(),
                                productDTO.getDateAdded(), categoryDTO.getId());
                        repository.productData().addItem(product);

                        TaxDTO taxDTO = productDTO.getTax();

                        Tax tax = new Tax(taxDTO.getName(), taxDTO.getValue());

                        repository.taxData().addItem(tax);

                        ProductTax productTax = new ProductTax(productDTO.getId(), taxDTO.getName());
                        repository.productTaxData().addItem(productTax);

                        for (VariantDTO variantDTO : productDTO.getVariants())
                        {
                            Variant variant = new Variant(variantDTO.getId(), variantDTO.getColor(), variantDTO.getSize(),
                                    variantDTO.getPrice(), productDTO.getId());
                            repository.variantData().addItem(variant);
                        }

                    }

                } else if (categoryDTO.getChildCategories() != null && categoryDTO.getChildCategories().size() != 0)
                {
                    for (Integer childCategoryId : categoryDTO.getChildCategories())
                    {
                        ParentChildCategoryMapping parentChildCategoryMapping = new ParentChildCategoryMapping(childCategoryId, categoryDTO.getId());
                        repository.parentChildCategoryMappingData().addItem(parentChildCategoryMapping);
                    }

                }
            }

            for (RankingCategoryDTO rankingCategoryDTO : masterdataDTO.getRankings())
            {
                for (ProductRankingDTO productRankingDTO : rankingCategoryDTO.getProductRankingDTOList())
                {
                    ProductRanking productRanking = new ProductRanking(productRankingDTO.getId(), productRankingDTO.getViewCount(), productRankingDTO.getOrderCount(), productRankingDTO.getShares());

                    repository.productRankingData().addItem(productRanking);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    @Override
    public void showLoading()
    {
        categoryView.showLoading();
    }

    @Override
    public void hideLoading()
    {
        categoryView.hideLoading();
    }


    void fetchSubCategoryFromDatabase(int parentCategoryId)
    {
        this.parentCategoryId = parentCategoryId;

        Disposable disposable = repository.categoryData().getParentSubCategoriesByParentCategoryId(parentCategoryId).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::parentCategories, categoryView::onError);
        compositeDisposable.add(disposable);

    }


    private void parentCategories(List<Category> categoryList)
    {
        parentCategoryList = categoryList;

        Disposable disposable= repository.categoryData().getChildSubCategoriesByParentCategoryId(parentCategoryId).
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::childCategoryData, categoryView::onError);
        compositeDisposable.add(disposable);


    }

    private void childCategoryData(List<CategoryAndMapping> categoryAndMappings)
    {
        childCategoryList = categoryAndMappings;

        List<CategoryGroup> categoryGroups = new ArrayList<>();

        for (Category parentCategory : parentCategoryList)
        {
            CategoryDTO parentCategoryDTO = new CategoryDTO(parentCategory.getCategoryId(), parentCategory.getCategoryName());

            List<CategoryDTO> childCategories = new ArrayList<>();
            for (CategoryAndMapping categoryAndMappingNew : childCategoryList)
            {
                if (parentCategory.getCategoryId() == categoryAndMappingNew.getParentId())
                {
                    CategoryDTO categoryDTO = new CategoryDTO(categoryAndMappingNew.getCategoryId(),
                            categoryAndMappingNew.getCategoryName());
                    childCategories.add(categoryDTO);
                }

            }

            CategoryGroup categoryGroup = new CategoryGroup(parentCategoryDTO, childCategories);
            categoryGroups.add(categoryGroup);

        }
        categoryView.populateSubCategories(categoryGroups);
    }


    @Override
    public void onDetach()
    {
        compositeDisposable.clear();
    }
}
