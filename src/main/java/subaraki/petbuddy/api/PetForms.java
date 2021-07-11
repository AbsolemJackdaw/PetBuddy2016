package subaraki.petbuddy.api;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.item.Item;
import subaraki.petbuddy.petform.SkinForm;

public class PetForms {

    private static ArrayList<IPetFormBase> all_forms = Lists.newArrayList();
    public static final IPetFormBase DEFAULT = new SkinForm();

    public static IPetFormBase getFormFromItem(Item item)
    {

        for (IPetFormBase form : all_forms)
        {
            if (form.getFormItem().equals(item))
                return form;
        }
        return DEFAULT;
    }

    /**
     * On FmlClientSetupEvent, call {@link PetForms#addForm(IPetFormBase)} to add a
     * pet form to the general list.
     */
    public static void addForm(IPetFormBase form)
    {

        all_forms.add(form);
    }

    /**
     * returns a copy of the list with all forms. Changing this list won't affect
     * anything. This can currently only used to itterate over all forms so their
     * layers can be added to the pet buddy renderer
     */
    public static ArrayList<IPetFormBase> all()
    {

        return Lists.newArrayList(all_forms);
    }

}
