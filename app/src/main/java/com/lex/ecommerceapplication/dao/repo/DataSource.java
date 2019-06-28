package com.lex.ecommerceapplication.dao.repo;

import java.util.List;

/**
 * @author DeepS
 * Base datasource
 * @param <T>
 */
public interface DataSource<T>
{

    void addItem(T item);

    void addItems(List<T> items);


}
