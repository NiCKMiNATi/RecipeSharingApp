package com.recipe.recipeapp.controller;

import com.recipe.recipeapp.model.Recipe;
import com.recipe.recipeapp.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

//    @GetMapping
//    public String getAllRecipes(Model model) {
//        model.addAttribute("recipes", recipeService.getAllRecipes());
//        return "recipes"; // Assuming you have a view named 'recipes.html' or 'recipes.jsp'
//    }

    @GetMapping
    public String getAllRecipes(@RequestParam(required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("recipes", recipeService.searchRecipes(search));
        } else {
            model.addAttribute("recipes", recipeService.getAllRecipes());
        }
        return "recipes";
    }


    @GetMapping("/new")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipe-form"; // Assuming you have a view named 'recipe-form.html' or 'recipe-form.jsp'
    }

    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute("recipe") Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/recipes"; // Redirect to the list of recipes after saving
    }

    @GetMapping("/{id}")
    public String getRecipeById(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe-detail"; // Assuming you have a view named 'recipe-details.html' or 'recipe-details.jsp'
    }

    @GetMapping("edit/{id}")
    public String editRecipeForm(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe-form"; // Assuming you have a view named 'recipe-form.html' or 'recipe-form.jsp'
    }

    @PostMapping("/update/{id}")
    public String updateRecipe(@PathVariable Long id, @ModelAttribute("recipe") Recipe recipe) {
        if (!id.equals(recipe.getId())) {
            throw new RuntimeException("Recipe ID mismatch: URL ID does not match the form ID.");
        }
        else {
            recipeService.updateRecipe(id, recipe);
        }
        return "redirect:/recipes"; // Redirect to the list of recipes after updating
    }

    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes"; // Redirect to the list of recipes after deletion
    }

    @PostMapping("/like/{id}")
    @ResponseBody  // Important: tells Spring to return JSON, not a view name
    public Map<String, Integer> likeRecipe(@PathVariable Long id) {
        Recipe recipe = recipeService.getRecipeById(id);
        recipe.setLikes(recipe.getLikes() + 1);
        recipeService.saveRecipe(recipe);

        // Return new like count as JSON
        Map<String, Integer> response = new HashMap<>();
        response.put("newLikeCount", recipe.getLikes());
        return response;
    }
}
