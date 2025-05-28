package com.recipe.recipeapp.service;

import com.recipe.recipeapp.model.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
    Recipe saveRecipe(Recipe recipe);
    Recipe updateRecipe(Long id, Recipe recipe);
    void deleteRecipe(Long id);

    List<Recipe> searchRecipes(String search);
}
