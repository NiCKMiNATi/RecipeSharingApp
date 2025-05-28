package com.recipe.recipeapp.service;

import com.recipe.recipeapp.model.Recipe;
import com.recipe.recipeapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found with id: " + id));
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe updateRecipe(Long id, Recipe recipe) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe not found with id: " + id);
        }
        recipe.setId(id);
        return recipeRepository.save(recipe);
    }

    @Override
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }

    public List<Recipe> searchRecipes(String search) {
        return recipeRepository.findByTitleContainingIgnoreCaseOrCategoryContainingIgnoreCase(search, search);
    }

}
