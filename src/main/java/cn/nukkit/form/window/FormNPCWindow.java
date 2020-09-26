package cn.nukkit.form.window;

import cn.nukkit.form.response.FormNPCResponse;

import com.google.gson.Gson;

/**
 * @author good777LUCKY
 */
public abstract class FormNPCWindow {

    private static final Gson GSON = new Gson();
    
    protected boolean closed = false;
    
    public String getJSONData() {
        return FormNPCWindow.GSON.toJson(this);
    }
    
    public abstract void setResponse(String data);
    
    public abstract FormNPCResponse getResponse();
    
    public boolean wasClosed() {
        return closed;
    }
}
