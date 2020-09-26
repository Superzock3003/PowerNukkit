package cn.nukkit.event.player;

import cn.nukkit.Player;
import cn.nukkit.event.HandlerList;
import cn.nukkit.form.response.FormNPCResponse;
import cn.nukkit.form.window.FormNPCWindow;

/**
 * @author good777LUCKY
 */
public class PlayerFormNPCRespondedEvent extends PlayerEvent {

    private static final HandlerList handlers = new HandlerList();
    
    public static HandlerList getHandlers() {
        return handlers;
    }
    
    protected int formID;
    protected FormNPCWindow window;
    
    protected boolean closed = false;
    
    public PlayerFormNPCRespondedEvent(Player player, int formID, FormNPCWindow window) {
        this.player = player;
        this.formID = formID;
        this.window = window;
    }
    
    public int getFormID() {
        return this.formID;
    }
    
    public FormNPCWindow getWindow() {
        return window;
    }
    
    public FormNPCResponse getResponse() {
        return window.getResponse();
    }
    
    public boolean wasClosed() {
        return window.wasClosed();
    }
}
