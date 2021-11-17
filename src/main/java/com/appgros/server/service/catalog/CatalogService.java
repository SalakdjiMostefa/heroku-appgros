package com.appgros.server.service.catalog;

import com.appgros.common.dto.CategoryDTO;
import com.appgros.common.dto.ItemDTO;
import com.appgros.common.dto.ProductDTO;
import com.appgros.common.exception.*;
import com.appgros.server.domain.category.Category;
import com.appgros.server.domain.category.CategoryDAO;
import com.appgros.server.domain.item.Item;
import com.appgros.server.domain.item.ItemDAO;
import com.appgros.server.domain.product.Product;
import com.appgros.server.domain.product.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class CatalogService implements ICatalogService {
    // ======================================
    // =             Attributes             =
    // ======================================
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ItemDAO itemDAO;

    // ======================================
    // =            Constructors            =
    // ======================================
    public CatalogService() {
    }

    // ======================================
    // =      Category Business methods     =
    // ======================================
    public CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CheckException, CreateException {

        if (categoryDTO == null)
            throw new CheckException("Category object is null");

        // Transforms DTO into domain object
        final Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());

        category.checkData();
        // Creates the object
        categoryDAO.save(category);

        // Transforms domain object into DTO
        final CategoryDTO result = transformCategory2DTO(category);

        return result;
    }

    public CategoryDTO findCategory(final Long categoryId) throws CreateException, CheckException {

        if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");

        final Category category = categoryDAO.findById(categoryId).get();

        // Transforms domain object into DTO
        final CategoryDTO categoryDTO = transformCategory2DTO(category);

        return categoryDTO;
    }

    public void deleteCategory(final Long categoryId) throws RemoveException, CheckException {

        checkId(categoryId);

        // Checks if the object exists
        categoryDAO.findById(categoryId);

        // Deletes the object
        categoryDAO.deleteById(categoryId);
    }

    public void updateCategory(final CategoryDTO categoryDTO) throws UpdateException, CheckException {

        if (categoryDTO == null)
            throw new CheckException("Category object is null");

        checkId(categoryDTO.getId());
        Category category = new Category();

        // Checks if the object exists
        category = categoryDAO.findById(categoryDTO.getId()).get();

        // Transforms DTO into domain object
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        // Updates the object
        categoryDAO.saveAndFlush(category);
    }

    public Collection findCategories() throws FinderException {

        // Finds all the objects
        final Collection categories = categoryDAO.findAll();

        // Transforms domain objects into DTOs
        final Collection categoriesDTO = transformCategories2DTOs(categories);

        return categoriesDTO;
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================
    public ProductDTO createProduct(final ProductDTO productDTO) throws CheckException {

        if (productDTO == null)
            throw new CheckException("Product object is null");
        if (productDTO.getCategoryDTO() == null || "".equals(productDTO.getCategoryDTO()))
            throw new CheckException("Invalid category id");

        // Finds the linked object
        Category category = null;
        category = categoryDAO.findById(productDTO.getCategoryDTO().getId()).get();

        // Transforms DTO into domain object
        final Product product = new Product();

        // Creates the object
        productDAO.save(product);

        // Transforms domain object into DTO
        final ProductDTO result = transformProduct2DTO(product);

        return result;
    }

    public ProductDTO findProduct(final Long productId) throws FinderException, CheckException {

        checkId(productId);

        // Finds the object
        final Product product = productDAO.findById(productId).get();

        // Transforms domain object into DTO
        final ProductDTO productDTO = transformProduct2DTO(product);

        return productDTO;
    }

    public void deleteProduct(final Long productId) throws RemoveException, CheckException {

        checkId(productId);

        // Checks if the object exists
        productDAO.findById(productId);

        // Deletes the object
        productDAO.deleteById(productId);
    }

    public void updateProduct(final ProductDTO productDTO) throws UpdateException, CheckException {

        if (productDTO == null)
            throw new CheckException("Product object is null");

        checkId(productDTO.getId());
        if (productDTO.getCategoryDTO().getId() == null)
            throw new CheckException("Invalid Category");

        Product product = null;

        // Checks if the object exists
        product = productDAO.findById(productDTO.getId()).get();

        // Finds the linked object
        Category category = null;
        category = categoryDAO.findById(productDTO.getCategoryDTO().getId()).get();

        // Transforms DTO into domain object
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category);

        // Updates the object
        productDAO.saveAndFlush(product);
    }

    public Collection findProducts() throws FinderException {

        // Finds all the objects
        final Collection products = productDAO.findAll();

        // Transforms domain objects into DTOs
        final Collection productsDTO = transformProducts2DTOs(products);

        return productsDTO;
    }

    public Collection findProductsByCategoryId(final Long categoryId) throws FinderException, CheckException {

        checkId(categoryId);

        // Finds all the products
        final Collection products = productDAO.findAllInCategory(categoryId);

        // Transforms domain objects into DTOs
        Collection productsDTO = transformProducts2DTOs(products);

        return productsDTO;
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================
    public ItemDTO createItem(final ItemDTO itemDTO) throws CreateException, CheckException {

        if (itemDTO == null)
            throw new CheckException("Item object is null");

        if (itemDTO.getProductDTO().getId() == null)
            throw new CheckException("Invalid Product id");
        checkId(itemDTO.getProductDTO().getId());

        // Finds the linked object
        Product product = null;
        product = productDAO.findById(itemDTO.getProductDTO().getId()).get();

        // Transforms DTO into domain object
//        final Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getUnitCost(), itemDTO.getStock(), product);
        final Item item = new Item();

        item.checkData();

        // Creates the object
        itemDAO.save(item);

        // Transforms domain object into DTO
        final ItemDTO result = transformItem2DTO(item);

        return result;
    }

    public ItemDTO findItem(final Long itemId) throws FinderException, CheckException {

        checkId(itemId);

        // Finds the object
        final Item item = itemDAO.findById(itemId).get();

        // Retreives the data for the linked object
        Product product = productDAO.findById(item.getProduct().getId()).get();
        item.setProduct(product);

        // Transforms domain object into DTO
        final ItemDTO itemDTO = transformItem2DTO(item);

        return itemDTO;
    }

    public void deleteItem(final Long itemId) throws RemoveException, CheckException {

        checkId(itemId);

        // Checks if the object exists
        itemDAO.findById(itemId);

        // Deletes the object
        itemDAO.deleteById(itemId);
    }

    public void updateItem(final ItemDTO itemDTO) throws UpdateException, CheckException {

        if (itemDTO == null)
            throw new UpdateException("Item object is null");

        checkId(itemDTO.getId());
        if (itemDTO.getProductDTO().getId() == null)
            throw new CheckException("Invalid Product in Item");

        Item item = null;

        // Checks if the object exists
        item = itemDAO.findById(itemDTO.getId()).get();

        // Finds the linked object
        Product product = null;
        product = productDAO.findById(itemDTO.getProductDTO().getId()).get();

        // Transforms DTO into domain object
        item.setName(itemDTO.getName());
        item.setUnitCost(itemDTO.getUnitCost());
         item.setProduct(product);

        item.checkData();

        // Updates the object
        itemDAO.saveAndFlush(item);
    }

    public Collection findItems() throws FinderException {

        // Finds all the objects
        final Collection items = itemDAO.findAll();

        // Transforms domain objects into DTOs
        final Collection itemsDTO = transformItems2DTOs(items);


        return itemsDTO;
    }

    public Collection findItemsByProductId(final Long productId) throws FinderException, CheckException {

        checkId(productId);

        // Finds all the items
        final Collection items = itemDAO.findAllInProduct(productId);

        // Transforms domain objects into DTOs
        final Collection itemsDTO = transformItems2DTOs(items);

        return itemsDTO;
    }

    public Collection searchItems(final String keyword) throws FinderException {

        // Search all the items
        final Collection items = itemDAO.search(keyword);

        // Transforms domain objects into DTOs
        final Collection itemsDTO = transformItems2DTOs(items);

        return itemsDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    // Category
    private CategoryDTO transformCategory2DTO(final Category category) {
        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Collection transformCategories2DTOs(final Collection categories) {
        final Collection categoriesDTO = new ArrayList();
        for (Iterator iterator = categories.iterator(); iterator.hasNext(); ) {
            final Category category = (Category) iterator.next();
            categoriesDTO.add(transformCategory2DTO(category));
        }
        return categoriesDTO;
    }

    // Product
    private ProductDTO transformProduct2DTO(final Product product) {
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        // Retreives the data for the linked object
        // Finds the linked object
        Category category = null;
        category = categoryDAO.findById(product.getCategory().getId()).get();
        productDTO.getCategoryDTO().setId(category.getId());
        return productDTO;
    }

    private Collection transformProducts2DTOs(final Collection products) {
        final Collection productsDTO = new ArrayList();
        for (Iterator iterator = products.iterator(); iterator.hasNext(); ) {
            final Product product = (Product) iterator.next();
            productsDTO.add(transformProduct2DTO(product));
        }
        return productsDTO;
    }

    // Item
    private ItemDTO transformItem2DTO(final Item item) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setUnitCost(item.getUnitCost());
        itemDTO.setQty(item.getQty());
        // Retreives the data for the linked object
        Product product = null;
        product = productDAO.findById(item.getProduct().getId()).get();
        itemDTO.getProductDTO().setId(product.getId());
        return itemDTO;
    }

    private Collection transformItems2DTOs(final Collection items) {
        final Collection itemsDTO = new ArrayList();
        for (Iterator iterator = items.iterator(); iterator.hasNext(); ) {
            final Item item = (Item) iterator.next();
            itemsDTO.add(transformItem2DTO(item));
        }
        return itemsDTO;
    }

    protected void checkId(final Long id) throws CheckException {
        if (id == null)
            throw new CheckException("Id should not be null or empty");
    }
}
