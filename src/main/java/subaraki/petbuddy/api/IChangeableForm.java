package subaraki.petbuddy.api;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface IChangeableForm {

    /**Called on slot change.
     * This is used in cats and rabbits to change the texture index.
     */
    public void onSlotInsert();
}
