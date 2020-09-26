package cn.nukkit.form.element;

import com.google.gson.JsonObject;

/**
 * @author good777LUCKY
 */
public class ElementNPCButton {

    private String buttonName;
    
    private Object data = null;
    
    private static final int MODE_BUTTON = 0;
    private static final int MODE_ON_CLOSE = 1;
    private int mode;
    
    private String text;
    
    private static final int TYPE_URL = 0;
    private static final int TYPE_COMMAND = 1;
    private static final int TYPE_INVALID = 2;
    private int type;
    
    public ElementNPCButton(String buttonName) {
        this(buttonName, null, MODE_BUTTON, "", TYPE_COMMAND);
    }
    
    public ElementNPCButton(String buttonName, Object data, int mode, String text, int type) {
        this.buttonName = buttonName;
        this.data = data;
        this.mode = mode;
        this.text = text;
        this.type = type;
    }
    
    public Object getData() {
        return data;
    }
    
    public int setData(Object data) {
        this.data = data;
    }
    
    public int getMode() {
        return mode;
    }
    
    public int setMode(int type) {
        this.mode = mode;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public int getType() {
        return type;
    }
    
    public int setType(int type) {
        this.type = type;
    }
    
    public String buttonToJSON() {
        JSONObject jsonObject = new JSONObject();
        
        jsonObject.put("button_name", name);
        jsonObject.put("data", -1);
        jsonObject.put("mode", mode);
        jsonObject.put("text", text == null ? "" : text);
        jsonObject.put("type", type);
        
        return jsonObject.toString().replace("-1", "null");
    }
}
