package forestry.storage;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.DistExecutor;

import forestry.api.modules.ForestryModule;
import forestry.api.storage.StorageManager;
import forestry.core.config.Constants;
import forestry.modules.BlankForestryModule;
import forestry.modules.ForestryModuleUids;
import forestry.modules.ISidedModuleHandler;
import forestry.modules.features.FeatureItem;
import forestry.storage.items.ItemCrated;
import forestry.storage.proxy.ProxyCrates;
import forestry.storage.proxy.ProxyCratesClient;

@ForestryModule(moduleID = ForestryModuleUids.CRATE, containerID = Constants.MOD_ID, name = "Crate", author = "SirSengir", url = Constants.URL, unlocalizedDescription = "for.module.crates.description")
public class ModuleCrates extends BlankForestryModule {

	public static final List<FeatureItem<ItemCrated>> crates = new ArrayList<>();

	@SuppressWarnings("NullableProblems")
	public static ProxyCrates proxy;

	public ModuleCrates() {
		proxy = DistExecutor.runForDist(() -> ProxyCratesClient::new, () -> ProxyCrates::new);
	}

	@Override
	public void setupAPI() {
		StorageManager.crateRegistry = new CrateRegistry();
	}

	public static void registerCrate(FeatureItem<ItemCrated> crate) {
		crates.add(crate);
	}

	@Override
	public ISidedModuleHandler getModuleHandler() {
		return proxy;
	}
}
