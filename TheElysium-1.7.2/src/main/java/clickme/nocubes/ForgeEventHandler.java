package clickme.nocubes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class ForgeEventHandler {

    private NoCubes noCubes;

    public ForgeEventHandler(NoCubes mod) {
        this.noCubes = mod;
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerLoggedInEvent event) {
        this.noCubes.notificatePlayerInChat(event.player);
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (this.noCubes.keyOpenSettings.isPressed())
            this.noCubes.openCubeSettingsGui();
    }

}
