package com.appgros.server.service.catalog;


import com.appgros.common.dto.CategoryDTO;
import com.appgros.common.dto.ItemDTO;
import com.appgros.common.dto.ProductDTO;
import com.appgros.common.exception.*;

import java.util.Collection;

/**
 * This interface gives a remote view of the CatalogServiceBean. Any distant client that wants to call
 * a method on the CatalogServiceBean has to use this interface.
 */
 public interface ICatalogService {

    // ======================================
    // =      Category Business methods     =
    // ======================================

    CategoryDTO createCategory(CategoryDTO categoryDTO) throws CheckException, CreateException;

    CategoryDTO findCategory(Long categoryId) throws FinderException, CheckException, CreateException;

    void deleteCategory(Long categoryId) throws RemoveException, CheckException;

    void updateCategory(CategoryDTO categoryDTO) throws UpdateException, CheckException;

    Collection findCategories() throws FinderException;

    // ======================================
    // =      Product Business methods     =
    // ======================================


    ProductDTO createProduct(ProductDTO productDTO) throws CheckException, CreateException;

    ProductDTO findProduct(Long productId) throws FinderException, CheckException;

    void deleteProduct(Long productId) throws RemoveException, CheckException;

    void updateProduct(ProductDTO productDTO) throws UpdateException, CheckException;

    Collection findProducts() throws FinderException;

    Collection findProductsByCategoryId(Long categoryId) throws FinderException, CheckException;

    // ======================================
    // =        Item Business methods       =
    // ======================================

    ItemDTO createItem(ItemDTO itemDTO) throws CreateException, CheckException, CreateException;

    ItemDTO findItem(Long itemId) throws FinderException, CheckException;

    void deleteItem(Long itemId) throws RemoveException, CheckException;

    void updateItem(ItemDTO itemDTO) throws UpdateException, CheckException;

    Collection findItems() throws FinderException;

    Collection findItemsByProductId(Long productId) throws FinderException, CheckException;

    Collection searchItems(String keyword) throws FinderException;

}
