package com.recipe.recipeapp.repository;

import com.recipe.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    public List<Recipe> findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(String title, String category);

}
