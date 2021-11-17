package com.appgros.web;

import com.appgros.common.dto.CategoryDTO;
import com.appgros.common.dto.ProductDTO;
import com.appgros.common.exception.CheckException;
import com.appgros.common.exception.CreateException;
import com.appgros.common.exception.FinderException;
import com.appgros.server.service.catalog.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/category")

public class CatalogController {

    @Autowired
    private ICatalogService iCatalogService;

    @PostMapping("/createCategory")
    CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws CheckException, CreateException, CreateException {
        return iCatalogService.createCategory(categoryDTO);
    }

    @GetMapping("/getCategory/{id}")
    CategoryDTO getCategory(@PathVariable("id") long id) throws FinderException, CheckException, CreateException {
        return iCatalogService.findCategory(id);
    }

    @GetMapping("/getAllCategories")
    Collection<CategoryDTO> getAllCategories() throws FinderException, CheckException, CreateException {
        return iCatalogService.findCategories();
    }

    @GetMapping("/getAllProducts")
    Collection<ProductDTO> getAllProducts() throws FinderException, CheckException, CreateException {
        return iCatalogService.findProducts();
    }

    @GetMapping("/getAllProductsByCategoryId/{id}")
    Collection<ProductDTO> getAllProductsByCategoryId(@PathVariable("id") long id) throws FinderException, CheckException, CreateException {
        return iCatalogService.findProductsByCategoryId(id);
    }

    @GetMapping("/getAllItems")
    Collection<ProductDTO> getAllItems() throws FinderException, CheckException, CreateException {
        return iCatalogService.findItems();
    }

    @GetMapping("/getAllItemsByProductId/{id}")
    Collection<ProductDTO> getAllItemsByProductId(@PathVariable("id") long id) throws FinderException, CheckException, CreateException {
        return iCatalogService.findItemsByProductId(id);
    }
}
