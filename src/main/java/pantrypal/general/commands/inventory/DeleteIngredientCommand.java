package pantrypal.general.commands.inventory;

import pantrypal.general.commands.Command;
import pantrypal.general.control.Ui;
import pantrypal.inventory.IngredientInventory;
import pantrypal.shoppinglist.ShoppingList;

public class DeleteIngredientCommand extends Command {
    private String name;

    public DeleteIngredientCommand() {
        super("deleteIngredient <name>", "Delete an ingredient from the inventory");
    }

    public DeleteIngredientCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(Ui ui, IngredientInventory inventory, ShoppingList shoppingList) {
        inventory.deleteIngredient(name);
    }
}
