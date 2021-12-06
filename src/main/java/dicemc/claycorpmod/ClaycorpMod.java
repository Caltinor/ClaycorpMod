package dicemc.claycorpmod;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


@Mod(ClaycorpMod.MOD_ID)
public class ClaycorpMod
{
	public static final String MOD_ID = "claycorp";
    //private static final Logger LOGGER = LogManager.getLogger();

    public ClaycorpMod() {
    	FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(RecipeSerializer.class, this::registerRecipeSerializers);
        MinecraftForge.EVENT_BUS.register(this);
        Registration.init();
    }
    
    public static class Registration {    	
    	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ClaycorpMod.MOD_ID);
    	
    	public static void init() {
			ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		}
    	
    	public static final RegistryObject<Item> DOORSTOP = ITEMS.register("doorstop", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    	public static final RegistryObject<Item> SQUISHED_BREAD = ITEMS.register("squished_bread", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD)
    			.food((new FoodProperties.Builder()).nutrition(5).saturationMod(0.6F).build())));
    }
    
    public void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event) {
    	event.getRegistry().registerAll(
    			new SimpleRecipeSerializer<>(SquishedBreadRecipe::new).setRegistryName("claycorp:squished_bread")
    	);
    }

}
