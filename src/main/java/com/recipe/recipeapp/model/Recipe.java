package com.recipe.recipeapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "recipes")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String category;

    @Column(length = 3000)
    private String ingredients;

    @Column(length = 5000)
    private String instructions;

    private int likes = 0;
}
