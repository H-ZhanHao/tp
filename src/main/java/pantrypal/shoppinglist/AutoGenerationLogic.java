// AutoGenerationLogic.java
package pantrypal.shoppinglist;

import pantrypal.inventory.IngredientInventory;
import pantrypal.inventory.Ingredient;
import java.util.Map;

public class AutoGenerationLogic {
    private ShoppingList shoppingList;
    private IngredientInventory inventory;

    public AutoGenerationLogic(ShoppingList shoppingList, IngredientInventory inventory) {
        this.shoppingList = shoppingList;
        this.inventory = inventory;
    }

    // Generate shopping list items for ingredients that are below their low stock alert threshold.
    public void generateShoppingList() {
        Map<String, Double> alerts = inventory.getLowStockAlerts();
        Map<String, Ingredient> ingredients = inventory.getInventory();

        for (Map.Entry<String, Double> entry : alerts.entrySet()) {
            String ingredientName = entry.getKey();
            double threshold = entry.getValue();

            // Retrieve the ingredient details from the inventory
            Ingredient ingredient = ingredients.get(ingredientName);
            if (ingredient != null && ingredient.getQuantity() <= threshold) {
                double required = threshold - ingredient.getQuantity();

                // Update existing item if present; if not, add a new item.
                boolean updated = shoppingList.updateItem(ingredientName, required, ingredient.getUnit(),
                        ingredient.getCategory());
                if (!updated) {
                    shoppingList.addItem(new ShoppingListItem(ingredientName, required, ingredient.getUnit(),
                            ingredient.getCategory()));
                }
            }
        }
    }
}
