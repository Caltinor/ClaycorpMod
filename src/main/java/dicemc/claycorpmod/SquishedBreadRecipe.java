package dicemc.claycorpmod;

import dicemc.claycorpmod.ClaycorpMod.Registration;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ObjectHolder;

public class SquishedBreadRecipe extends CustomRecipe{
	public SquishedBreadRecipe(ResourceLocation idIn) {super(idIn);}

	@Override
	public boolean matches(CraftingContainer inv, Level level) {
		boolean doorstop = false;
		boolean bread = false;
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem().equals(Items.BREAD))
				bread=true;
			else if (inv.getItem(i).getItem().equals(ClaycorpMod.Registration.DOORSTOP.get()))
				doorstop=true;
			if (doorstop && bread) break;
		}
		return doorstop && bread;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
		NonNullList<ItemStack> out = NonNullList.create();
		for (int i = 0; i < inv.getContainerSize(); i++) {
			if (inv.getItem(i).getItem().equals(ClaycorpMod.Registration.DOORSTOP.get()))
				out.add(new ItemStack(Registration.DOORSTOP.get()));
			else 
				out.add(ItemStack.EMPTY);
		}
		return out;
		
	}

	@Override
	public ItemStack assemble(CraftingContainer inv) {
		return new ItemStack(ClaycorpMod.Registration.SQUISHED_BREAD.get());
	}

	@Override
	public boolean canCraftInDimensions(int w, int h) {return w * h >= 2;}

	@Override
	public RecipeSerializer<?> getSerializer() {return SERIALIZER;}
	
	@ObjectHolder(ClaycorpMod.MOD_ID+":squished_bread")
	public static SimpleRecipeSerializer<SquishedBreadRecipe> SERIALIZER;
}
