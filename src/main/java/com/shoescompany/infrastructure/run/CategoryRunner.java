package com.shoescompany.infrastructure.run;


import com.shoescompany.application.services.interfaces.ICategoryService;
import com.shoescompany.domain.dtos.CategoryDTO;
import com.shoescompany.domain.records.CategoryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class CategoryRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRunner.class);
    private final ICategoryService categoryService;

    public CategoryRunner(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Running at application startup...");

        // Ejecutar los métodos al inicio
        findAll();
        findById(1L);
        save(new CategoryDTO("Test categoria", "https://pixabay.com/images/search/testing/"));
        update(1L, new CategoryDTO("Categoría Actualizada", "https://pngtree.com/free-png-vectors/example"));
        remove(2L);
        activate(3L);
    }

    public void findAll() {
        logger.info("Fetching all categories.");
        List<CategoryResponse> categories = categoryService.findAll();
        logger.info("Total categories found: {}", categories.size());
    }

    public void findById(@PathVariable Long id) throws Exception {
        logger.info("Fetching category with ID: {}", id);
        CategoryResponse category = categoryService.findById(id);
        logger.info("Category found: {}", category);
    }

    public void save(@RequestBody @Validated CategoryDTO categoryDTO) throws Exception {
        logger.info("Saving new category: {}", categoryDTO.getCategory());
        categoryService.save(categoryDTO);
        logger.info("Category saved successfully.");
    }

    public void update(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws Exception {
        logger.info("Updating category with ID: {}", id);
        categoryService.update(id, categoryDTO);
        logger.info("Category updated successfully.");
    }

    public void remove(@PathVariable Long id) throws Exception {
        logger.info("Deleting category with ID: {}", id);
        categoryService.delete(id);
        logger.info("Category deleted successfully.");
    }

    public void activate(@PathVariable Long id) throws Exception {
        logger.info("Activating category with ID: {}", id);
        categoryService.activate(id);
        logger.info("Category activated successfully.");
    }
}