package com.exam.controllers;

import com.exam.models.exam.Category;
import com.exam.services.impl.CategoryServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    //Add-Category
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory ( category );

        return ResponseEntity.ok (category1);
    }

    //Update-Category
    @PutMapping("/{categoryId}")
    public Category updateCategory(@RequestBody Category category,@PathVariable Long categoryId){
        return this.categoryService.updateCategory ( category,categoryId );
    }

    //Get-One-Category
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok (this.categoryService.getCategory ( categoryId ));
    }

    //Get-All=Categories
    @GetMapping("/")
    public Set<Category> getAllCategory(){
      return this.categoryService.getCategories ();
    }
    //Delete-Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
        this.categoryService.deleteCategory ( categoryId );
        return ResponseEntity.ok ("!!! Category deleted successfully !!!");
    }




}
