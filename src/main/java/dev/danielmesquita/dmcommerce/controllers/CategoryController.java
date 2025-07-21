package dev.danielmesquita.dmcommerce.controllers;

import dev.danielmesquita.dmcommerce.dtos.CategoryDTO;
import dev.danielmesquita.dmcommerce.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOS = service.findAll();
        return ResponseEntity.ok(categoryDTOS);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDTO>> getAllCategories(Pageable pageable) {
        Page<CategoryDTO> categoryDTOS = service.findAllPageable(pageable);
        return ResponseEntity.ok(categoryDTOS);
    }
}
