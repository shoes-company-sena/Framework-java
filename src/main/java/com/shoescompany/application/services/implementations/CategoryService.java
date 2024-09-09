package com.shoescompany.application.services.implementations;

import com.shoescompany.application.services.interfaces.ICategoryService;
import com.shoescompany.domain.dtos.CategoryDTO;
import com.shoescompany.domain.entities.Category;
import com.shoescompany.domain.enums.State;
import com.shoescompany.domain.records.CategoryResponse;
import com.shoescompany.infrastructure.repositories.CategoryRepository;
import com.shoescompany.infrastructure.utils.ModelMapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapperUtils modelMapperUtils;

    public CategoryService(CategoryRepository categoryRepository, ModelMapperUtils modelMapperUtils) {
        this.categoryRepository = categoryRepository;
        this.modelMapperUtils = modelMapperUtils;
    }

    private Category findByCategory(Long id) throws Exception {
        return this.categoryRepository.findById(id).orElseThrow(() -> new Exception("Category not found"));
    }

    /**
     *
     * @return
     */
    // Este metodo es para traer todas las categorias
    @Override
    public List<CategoryResponse> findAll() {
        return this.categoryRepository.findAll().stream()
                .map(this::mapToCategoryResponse)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    // Este es para encontrar una categoria por medio del id
    @Override
    public CategoryResponse findById(Long id) throws Exception {
        return mapToCategoryResponse(findByCategory(id));
    }

    /**
     *
     * @param categoryDTO
     * @return
     */
    // Este es para guardar la categoria
    @Override
    public CategoryResponse save(CategoryDTO categoryDTO) {
        Category categoryData = modelMapperUtils.map(categoryDTO, Category.class);
        Category category = categoryRepository.save(categoryData);
        return mapToCategoryResponse(category);
    }

    /**
     *
     * @param id
     * @param categoryDTO
     * @throws Exception
     */
    // Este metodo es para actualizar una categoria a creada
    @Override
    public void update(Long id, CategoryDTO categoryDTO) throws Exception {
        Category category = findByCategory(id);
        modelMapperUtils.mapVoid(categoryDTO, category);
        categoryRepository.save(category);
    }

    /**
     *
     * @param id
     * @throws Exception
     */
    // Este metodo es para eliminar,
    // aunque es un eliminado logico, el estado se coloca en Inactivo
    @Override
    public void delete(Long id) throws Exception {
        changeState(id, State.Inactivo);
    }


    /**
     *
     * @param id
     * @throws Exception
     */
    // Este metodo es para activar,
    // aunque es un activado logico, el estado se coloca en Activo
    @Override
    public void activate(Long id) throws Exception {
        changeState(id, State.Activo);
    }

    private void changeState(Long id, State state) throws Exception {
        Category category = findByCategory(id);
        category.setState(state);
        categoryRepository.save(category);
    }

    /**
     *
     * @param category
     * @return
     */
    // Este metodo es para mapear la respuesta a la API
    private CategoryResponse mapToCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getCategory(),
                category.getImage()
        );
    }
}
