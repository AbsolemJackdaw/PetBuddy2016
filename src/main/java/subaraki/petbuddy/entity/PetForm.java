package subaraki.petbuddy.entity;

import subaraki.petbuddy.mod.PetBuddy;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import subaraki.petbuddy.mod.PetBuddy;

public class PetForm {

	private static final ResourceLocation TEXTURE_SHEEP = new ResourceLocation("textures/entity/sheep/sheep.png");
	private static final ResourceLocation TEXTURE_HUSK = new ResourceLocation("textures/entity/zombie/husk.png");
	private static final ResourceLocation TEXTURE_COW = new ResourceLocation("textures/entity/cow/cow.png");
	private static final ResourceLocation TEXTURE_SQUID = new ResourceLocation("textures/entity/squid.png");
	private static final ResourceLocation TEXTURE_PIG = new ResourceLocation("textures/entity/pig/pig.png");
	private static final ResourceLocation TEXTURE_CHICKEN = new ResourceLocation("textures/entity/chicken.png");
	private static final ResourceLocation TEXTURE_SPIDER = new ResourceLocation("textures/entity/spider/spider.png");
	private static final ResourceLocation TEXTURE_CAVE_SPIDER = new ResourceLocation("textures/entity/spider/cave_spider.png");
	private static final ResourceLocation TEXTURE_GHAST = new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation TEXTURE_BLAZE = new ResourceLocation("textures/entity/blaze.png");
	private static final ResourceLocation TEXTURE_MOOSHROOM = new ResourceLocation("textures/entity/cow/mooshroom.png");
	private static final ResourceLocation TEXTURE_BAT = new ResourceLocation("textures/entity/bat.png");
	private static final ResourceLocation TEXTURE_POLARBEAR = new ResourceLocation("textures/entity/bear/polarbear.png");
	private static final ResourceLocation TEXTURE_ENDERMAN = new ResourceLocation("textures/entity/enderman/enderman.png");
	private static final ResourceLocation TEXTURE_CREEPER = new ResourceLocation("textures/entity/creeper/creeper.png");
	private static final ResourceLocation TEXTURE_PIGMAN = new ResourceLocation("textures/entity/zombie_pigman.png");
	private static final ResourceLocation TEXTURE_ENDERMITE = new ResourceLocation("textures/entity/endermite.png");
	private static final ResourceLocation TEXTURE_SILVERFISH = new ResourceLocation("textures/entity/silverfish.png");
	private static final ResourceLocation TEXTURE_IRON_GOLEM= new ResourceLocation("textures/entity/iron_golem.png");
	private static final ResourceLocation TEXTURE_SNOW_MAN = new ResourceLocation("textures/entity/snowman.png");
	private static final ResourceLocation TEXTURE_WITCH = new ResourceLocation("textures/entity/witch.png");
	private static final ResourceLocation TEXTURE_DOGE = new ResourceLocation("textures/entity/wolf/wolf.png");
    private static final ResourceLocation GUARDIAN_TEXTURE = new ResourceLocation("textures/entity/guardian.png");

	public static final ResourceLocation[] TEXTURE_RABBIT = new ResourceLocation[]{
			new ResourceLocation("textures/entity/rabbit/brown.png"),
			new ResourceLocation("textures/entity/rabbit/white.png"),
			new ResourceLocation("textures/entity/rabbit/black.png"),
			new ResourceLocation("textures/entity/rabbit/gold.png"),
			new ResourceLocation("textures/entity/rabbit/salt.png"),
			new ResourceLocation("textures/entity/rabbit/white_splotched.png")
	};
	public static final ResourceLocation[] TEXTURE_ZOMBIE = new ResourceLocation[]{
			new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_farmer.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_librarian.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_priest.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_smith.png"),
			new ResourceLocation("textures/entity/zombie_villager/zombie_butcher.png"),
			new ResourceLocation("textures/entity/zombie/zombie.png"),
			new ResourceLocation("textures/entity/zombie/husk.png")
	};
	public static final ResourceLocation TEXTURE_SKELETON[] = new ResourceLocation[]{
			new ResourceLocation("textures/entity/skeleton/skeleton.png"),
			new ResourceLocation("textures/entity/skeleton/wither_skeleton.png"),
			new ResourceLocation("textures/entity/skeleton/stray.png")
	};
	public static final ResourceLocation[] TEXTURE_VILLAGER = new ResourceLocation[]{
			new ResourceLocation("textures/entity/villager/villager.png"),
			new ResourceLocation("textures/entity/villager/farmer.png"),
			new ResourceLocation("textures/entity/villager/librarian.png"),
			new ResourceLocation("textures/entity/villager/priest.png"),
			new ResourceLocation("textures/entity/villager/smith.png"),
			new ResourceLocation("textures/entity/villager/butcher.png")
	};
	public static final ResourceLocation[] TEXTURE_CATE = new ResourceLocation[]{
			new ResourceLocation("textures/entity/cat/black.png"),
			new ResourceLocation("textures/entity/cat/ocelot.png"),
			new ResourceLocation("textures/entity/cat/red.png"),
			new ResourceLocation("textures/entity/cat/siamese.png")
	};
	public static final ResourceLocation[] TEXTURE_SHULKER = new ResourceLocation[] {
			new ResourceLocation("textures/entity/shulker/shulker_white.png"),
			new ResourceLocation("textures/entity/shulker/shulker_orange.png"),
			new ResourceLocation("textures/entity/shulker/shulker_magenta.png"),
			new ResourceLocation("textures/entity/shulker/shulker_light_blue.png"),
			new ResourceLocation("textures/entity/shulker/shulker_yellow.png"),
			new ResourceLocation("textures/entity/shulker/shulker_lime.png"),
			new ResourceLocation("textures/entity/shulker/shulker_pink.png"),
			new ResourceLocation("textures/entity/shulker/shulker_gray.png"),
			new ResourceLocation("textures/entity/shulker/shulker_silver.png"),
			new ResourceLocation("textures/entity/shulker/shulker_cyan.png"),
			new ResourceLocation("textures/entity/shulker/shulker_purple.png"),
			new ResourceLocation("textures/entity/shulker/shulker_blue.png"),
			new ResourceLocation("textures/entity/shulker/shulker_brown.png"),
			new ResourceLocation("textures/entity/shulker/shulker_green.png"),
			new ResourceLocation("textures/entity/shulker/shulker_red.png"), 
			new ResourceLocation("textures/entity/shulker/shulker_black.png")
	};
	public static final ResourceLocation[] TEXTURE_PARROT = new ResourceLocation[] {
			new ResourceLocation("textures/entity/parrot/parrot_red_blue.png"),
			new ResourceLocation("textures/entity/parrot/parrot_blue.png"), 
			new ResourceLocation("textures/entity/parrot/parrot_green.png"), 
			new ResourceLocation("textures/entity/parrot/parrot_yellow_blue.png"),
			new ResourceLocation("textures/entity/parrot/parrot_grey.png")
	};
	private static final ResourceLocation[] LLAMA_TEXTURES = new ResourceLocation[] {
			new ResourceLocation("textures/entity/llama/llama_creamy.png"),
			new ResourceLocation("textures/entity/llama/llama_white.png"),
			new ResourceLocation("textures/entity/llama/llama_brown.png"),
			new ResourceLocation("textures/entity/llama/llama_gray.png")
	};

	public static ModelBase getModelForForm(EntityPetBuddy entity){
		switch (entity.getForm()){
		case ZOMBIE :
			return entity.getTextureIndex() >= 6 ? PetBuddy.proxy.getBiped() : PetBuddy.proxy.getVillagerZombie();
		case STEVE:
		case FRIEND:
			return entity.getModelType().equals("default") ? PetBuddy.proxy.getSteve() : PetBuddy.proxy.getAlex();

		default:return entity.getForm().getModel();
		}
	}

	/**
	 * returns the appropriate texture for given form.
	 * EntityPetBuddy argument needed for player skin
	 * */
	public static ResourceLocation getTextureForModel(EntityPetBuddy entity){

		if(entity.getForm().hasMultiTexture() && entity.getTextureIndex() < entity.getForm().getReslocArray().length)
			return entity.getForm().getReslocArray()[entity.getTextureIndex()];

		switch (entity.getForm()) {
		case FRIEND : 
			return entity.FRIENDSKIN;
		case STEVE: 
			if(entity.getOwner() != null && ((EntityPlayer)entity.getOwner()) instanceof AbstractClientPlayer)
				return ((AbstractClientPlayer)entity.getOwner()).getLocationSkin();

		default : 
			return entity.getForm().getResloc();
		}
	}

	public static float getRenderOffset(EnumPetform form){
		switch (form) {
		case BAT : case SQUID: return 0.35f;
		case BLAZE : return 0.7F;
		case GHAST : return 0.5f;
		default : return 0F;
		}
	}

	public static float getScaleForForm(EnumPetform form){
		switch (form) {
		case SPIDER : 
			return 0.45f;
		case ENDERMITE : case SILVERFISH : case CHICKEN :
			return 0.56f;
		case RABBIT :
			return 0.6f;
		case BAT : case SQUID :
			return 0.25f;
		default : 
			return 0.4F;
		}
	}

	public static EnumPetform getFormFromItem(ItemStack stack){
		if(stack.isEmpty())
			return EnumPetform.STEVE;

		for(EnumPetform form : EnumPetform.values()){
			if(form.itemstacks == null) continue;
			for(ItemStack identifier : form.itemstacks){
				if(ItemStack.areItemsEqual(identifier, stack))
					return form;
			}
		}

		return EnumPetform.STEVE;
	}

	public static enum EnumPetform{
		STEVE((ResourceLocation)null, PetBuddy.proxy.getBiped(), null, (ItemStack[])null),
		SHEEP(TEXTURE_SHEEP, PetBuddy.proxy.getSheep(), new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Blocks.WOOL)}),
		ZOMBIE(TEXTURE_ZOMBIE, PetBuddy.proxy.getBiped(), null, new ItemStack[]{new ItemStack(Items.ROTTEN_FLESH)}),
		SKELETON(TEXTURE_SKELETON, PetBuddy.proxy.getSkeleton(), null,  new ItemStack[]{new ItemStack(Items.ARROW)}),
		PIG(TEXTURE_PIG, PetBuddy.proxy.getPig(), new float[]{7,6,-15}, new ItemStack[]{new ItemStack(Items.PORKCHOP)}),
		COW(TEXTURE_COW, PetBuddy.proxy.getCow(), new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.LEATHER), new ItemStack(Items.BEEF)}),
		SQUID(TEXTURE_SQUID, PetBuddy.proxy.getSquid(), new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.DYE,1,0)}),
		CHICKEN(TEXTURE_CHICKEN, PetBuddy.proxy.getChicken(), new float[]{4,2,-16}, new ItemStack[]{new ItemStack(Items.FEATHER), new ItemStack(Items.CHICKEN)}),
		SPIDER(TEXTURE_SPIDER, PetBuddy.proxy.getSpider(), new float[]{5,5,0}, new ItemStack[]{new ItemStack(Items.SPIDER_EYE)}),
		CAVESPIDER(TEXTURE_CAVE_SPIDER, PetBuddy.proxy.getSpider(), new float[]{5,5,0}, new ItemStack[]{new ItemStack(Items.FERMENTED_SPIDER_EYE)}),
		BLAZE(TEXTURE_BLAZE, PetBuddy.proxy.getBlaze(), new float[]{8, 2 ,-8}, new ItemStack[]{new ItemStack(Items.BLAZE_ROD)}),
		GHAST(TEXTURE_GHAST, PetBuddy.proxy.getGhast(), new float[]{8, 7,-22}, new ItemStack[]{new ItemStack(Items.GHAST_TEAR)}),
		MOOSHROOM(TEXTURE_MOOSHROOM, PetBuddy.proxy.getCow(), new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Blocks.BROWN_MUSHROOM), new ItemStack(Blocks.RED_MUSHROOM)}),
		BAT(TEXTURE_BAT, PetBuddy.proxy.getBat(), new float[]{0f, 2,-20}, new ItemStack[]{new ItemStack(Items.COOKIE)}),
		POLARBEAR(TEXTURE_POLARBEAR, PetBuddy.proxy.getPolarBear(), new float[]{7,6,-9}, new ItemStack[]{new ItemStack(Items.FISH,1,0), new ItemStack(Items.FISH, 1, 1)}),
		VILLAGER(TEXTURE_VILLAGER, PetBuddy.proxy.getVillager(), new float[]{-0.5f,6.5f,-6.5f}, new ItemStack[]{new ItemStack(Items.BOOK)}),
		ENDERMAN(TEXTURE_ENDERMAN, PetBuddy.proxy.getEnderman(), new float[]{0, 2, -25}, new ItemStack[]{new ItemStack(Items.ENDER_PEARL), new ItemStack(Items.ENDER_EYE)}),
		CREEPER(TEXTURE_CREEPER, PetBuddy.proxy.getCreeper(), new float[]{4,3f,-10}, new ItemStack[]{new ItemStack(Items.GUNPOWDER)}),
		RABBIT(TEXTURE_RABBIT, PetBuddy.proxy.getRabbit(), new float[]{3,0,-20}, new ItemStack[]{new ItemStack(Items.RABBIT_FOOT)}),
		ZOMBIEPIGMAN(TEXTURE_PIGMAN, PetBuddy.proxy.getBiped(), null, new ItemStack[]{new ItemStack(Items.NETHER_WART)}),
		ENDERMITE(TEXTURE_ENDERMITE, PetBuddy.proxy.getEndermite(), new float[]{0,0,-18}, new ItemStack[]{new ItemStack(Blocks.END_STONE)}),
		SILVERFISH(TEXTURE_SILVERFISH, PetBuddy.proxy.getSilverfish(), new float[]{0,0,-18}, new ItemStack[]{new ItemStack(Blocks.STONEBRICK)}),
		IRONGOLEM(TEXTURE_IRON_GOLEM, PetBuddy.proxy.getIronGolem(), new float[]{11,4,-25}, new ItemStack[]{new ItemStack(Blocks.RED_FLOWER)}),
		SNOWMAN(TEXTURE_SNOW_MAN, PetBuddy.proxy.getSnowman(), new float[]{10,2,-3}, new ItemStack[]{new ItemStack(Blocks.SNOW)}),
		CATE(TEXTURE_CATE, PetBuddy.proxy.getCate(), new float[]{0,-1,-12}, new ItemStack[]{new ItemStack(Items.COOKED_FISH,1,0), new ItemStack(Items.COOKED_FISH, 1, 1)}),
		WITCH(TEXTURE_WITCH, PetBuddy.proxy.getVillager(), new float[]{-0.5f,6.5f,-6.5f}, new ItemStack[]{new ItemStack(Items.POTIONITEM)}),
		DOGE(TEXTURE_DOGE, PetBuddy.proxy.getDoge(), new float[]{4,0,-12}, new ItemStack[]{new ItemStack(Items.BONE)}),
		FRIEND((ResourceLocation)null, null, null, new ItemStack[]{new ItemStack(Items.NAME_TAG)}),
		SHULKER(TEXTURE_SHULKER, PetBuddy.proxy.getShulker(), new float[]{-9,0,-16},  new ItemStack[]{new ItemStack(Items.CHORUS_FRUIT)}),
		PARROT(TEXTURE_PARROT, PetBuddy.proxy.getParrot(), new float[]{-3,5,-20},  new ItemStack[]{new ItemStack(Items.WHEAT_SEEDS),new ItemStack(Items.PUMPKIN_SEEDS),new ItemStack(Items.BEETROOT_SEEDS),new ItemStack(Items.MELON_SEEDS)}),
		LLAMA(LLAMA_TEXTURES, PetBuddy.proxy.getLLama(), new float[]{7,6,-9},  new ItemStack[]{new ItemStack(Blocks.CARPET)}),
		GUARDIAN(GUARDIAN_TEXTURE, PetBuddy.proxy.getGuardian(), new float[]{0,0,0},  new ItemStack[]{new ItemStack(Items.PRISMARINE_SHARD)});

		private ResourceLocation resloc;
		private ResourceLocation[] reslocArray;
		private ModelBase model;
		private ItemStack[] itemstacks;
		private float[] swordoffset;

		private boolean multitexture;

		private EnumPetform(ResourceLocation resloc, ModelBase model, float[] swordOffset, ItemStack... stack){
			this.resloc = resloc;
			this.model = model;
			this.itemstacks = stack;
			this.swordoffset = swordOffset;
		}

		private EnumPetform(ResourceLocation[] reslocArray, ModelBase model, float[] swordOffset, ItemStack... stack){
			this.multitexture = true;
			this.reslocArray = reslocArray;
			this.model = model;
			this.itemstacks = stack;
			this.swordoffset = swordOffset;
		}

		public ResourceLocation getResloc() {
			return resloc;
		}

		public ResourceLocation[] getReslocArray() {
			return reslocArray;
		}

		public ModelBase getModel() {
			return model;
		}

		public ItemStack[] getItemStacks() {
			return itemstacks;
		}

		public float[] getSwordOffset() {
			return swordoffset;
		}

		public boolean hasMultiTexture(){
			return multitexture;
		}
	}
}
