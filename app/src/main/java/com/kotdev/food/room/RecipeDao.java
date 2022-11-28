package com.kotdev.food.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.kotdev.food.app.models.Recipe;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RecipeDao {

    @Insert(onConflict = IGNORE)
    long[] insertRecipes(Recipe... recipes);

    @Insert(onConflict = REPLACE)
    void insertRecipe(Recipe recipe);

    @Query("UPDATE recipes SET title = :title, publisher = :publisher, image_url = :image_url, " +
            "social_rank = :social_rank WHERE recipe_id = :recipe_id")
    void updateRecipe(String recipe_id, String title, String publisher, String image_url, String social_rank);


    @Query("SELECT * FROM recipes WHERE title LIKE '%' || :query || '%' OR  ingredients LIKE '%' || :query || '%'" +
            " ORDER BY social_rank DESC LIMIT (:pageNumber * 30)")
    LiveData<List<Recipe>> searchRecipes(String query, int pageNumber);

    @Query("SELECT * FROM recipes WHERE recipe_id = :recipeId")
    LiveData<Recipe> getRecipe(String recipeId);


}