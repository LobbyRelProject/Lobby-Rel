package de.phyrone.lobbyrel.hotbar;

import de.phyrone.lobbyrel.config.Config;
import de.phyrone.lobbyrel.config.ItemsConfig;
import de.phyrone.lobbyrel.gui.LobbySwitcherGUI;
import de.phyrone.lobbyrel.gui.PlayerHiderBrewGUI;
import de.phyrone.lobbyrel.gui.SettingsGUI;
import de.phyrone.lobbyrel.hotbar.api.Hotbar;
import de.phyrone.lobbyrel.hotbar.api.HotbarAction;
import de.phyrone.lobbyrel.hotbar.api.HotbarItem;
import de.phyrone.lobbyrel.hotbar.api.HotbarItemAction;
import de.phyrone.lobbyrel.lib.ItemBuilder;
import de.phyrone.lobbyrel.lib.LobbyItem;
import de.phyrone.lobbyrel.navigator.NavigatorManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class MainHotbar {
	static Hotbar hotbar = new Hotbar();
	public static void setup() {
		hotbar = new Hotbar();
		hotbar.setAction(new HotbarAction() {
			
			@Override
			public void onSwitchSite(Player player, int from, int to) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onOpen(Player player) {
			}
			
			@Override
			public void onClose(Player player) {
				
			}
		});
		HotbarItem nav = new HotbarItem(new ItemBuilder(Material.COMPASS).displayname("&l&6Navigator").build());
		nav.setAction(new HotbarItemAction() {
			@Override
			public ItemStack onSelect(Player player) {
				return ItemsConfig.getInstance().getItem("Navigator"
						,new ItemBuilder(Material.COMPASS).displayname("&l&6Navigator").build()).getAsItemStack(player);
			}

			@Override
			public void onClick(PlayerInteractEvent event, Boolean rightClick) {
				if(rightClick)NavigatorManager.openNavigator(event.getPlayer());
			}
		});
		if(Config.getBoolean("Items.Navigator.Enabled", true))
		hotbar.setItem(Config.getInt("Items.Navigator.Slot", 0), nav);
		if(Config.getBoolean("Items.PlayerHider.Enabled", true))
		hotbar.setItem(Config.getInt("Items.PlayerHider.Slot", 1), new HotbarItem(new ItemBuilder(Material.BLAZE_ROD).displayname("&6PlayerHider").build()).setAction(new HotbarItemAction() {
			
			@Override
			public ItemStack onSelect(Player player) {
				return ItemsConfig.getInstance().getItem("PlayerHider",
				 new ItemBuilder(Material.BLAZE_ROD).displayname( "&6PlayerHider" ).build()).getAsItemStack(player);
			}
			
			@Override
			public void onClick(PlayerInteractEvent event, Boolean rightClick) {
				if(rightClick)PlayerHiderBrewGUI.open(event.getPlayer());
			}
		}));
		if(Config.getBoolean("Items.Settings.Enabled", true))
		hotbar.setItem(Config.getInt("Items.Settings.Slot", 8), new HotbarItem(new ItemBuilder(Material.REDSTONE_COMPARATOR).displayname("&cSettings").build()).setAction(new HotbarItemAction() {
			
			@Override
			public ItemStack onSelect(Player player) {
				return ItemsConfig.getInstance().getItem("Settings", new LobbyItem()
						.setMaterial(Material.SKULL_ITEM).setDisplayName("&6Settings").setPlayerHead(true).setSkin(
								"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzMzZGNmYjRkYTEwMTc3MjY0OTY4YjQ0OWU3MjRhZGViZWUzYmMzM2I3MmJhZTg1ODQyYjRhYWI5YmQ5YzRkYiJ9fX0="
								)).getAsItemStack(player);
			}
			
			@Override
			public void onClick(PlayerInteractEvent event, Boolean rightClick) {
                if (rightClick) SettingsGUI.open(event.getPlayer(), 0);
			}
		}));
		if(Config.getBoolean("Items.Swticher.Enabled", true))
		hotbar.setItem(Config.getInt("Items.Swticher.Slot", 7), new HotbarItem(new ItemBuilder(Material.WATCH).displayname("&6Lobbys").build()).setAction(new HotbarItemAction() {
			
			@Override
			public ItemStack onSelect(Player player) {
				return ItemsConfig.getInstance().getItem("LobbySwitcher", new ItemBuilder(Material.WATCH).displayname("&6Lobbys").build()).getAsItemStack(player);
			}
			
			@Override
			public void onClick(PlayerInteractEvent event, Boolean rightClick) {
				if(rightClick)LobbySwitcherGUI.open(event.getPlayer());
			}
		}));
	}public static void open(Player p) {
		hotbar.open(p, 0);
	}public static Hotbar getHotbar() {
		return hotbar;
	}public static void addItem(int slot,HotbarItem item) {
		hotbar.setItem(slot, item);
	}
	public static void setHotbar(Hotbar hotbar) {
		MainHotbar.hotbar = hotbar;
	}
	
	

}
