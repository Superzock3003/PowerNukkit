package cn.nukkit.form.window;

import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.form.element.ElementNPCButton;
import cn.nukkit.form.response.FormNPCResponseStudent;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormNPCWindowStudent extends FormNPCWindow {

    private final String type = "student";
    private String message = "";
    private List<ElementButton> buttons;
    
    private Entity model;
    
    private FormNPCResponseStudent response = null;
    
    public FormNPCWindowStudent(Entity model, String message) {
        this(model, message, new ArrayList<>());
    }
    
    public FormNPCWindowStudent(Entity model, String message, List<ElementNPCButton> buttons) {
        this.model = model;
        this.message = message;
        this.buttons = buttons;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(Entity model) {
        // Player is not able
        if (model instanceof Player) {
            return;
        }
        
        if (this.entity != null) {
            return;
        }
        
        this.model = model;
        
        EntityMetadata property = model.getDataProperties();
        property.putByte(39, 1);
        property.putString(99, this.message);
        property.putString(41, buttonsToJson()); // Buttons are save as JSON
    }
    
    public void removeModel() {
        this.model = null;
        
        model.getDataProperties().putByte(39, 0);
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
        
        if (entity != null) {
             model.getDataProperties().putString(99, message);
        }
    }
    
    public List<ElementNPCButton> getButtons() {
        return buttons;
    }
    
    public void addButton(ElementNPCButton button) {
        this.buttons.add(button);
    }
    
    public void setButtons(List<ElementNPCButton> buttons) {
        this.buttons = buttons;
    }
    
    public FormNPCResponseStudent getResponse() {
        return response;
    }
    
    public void setResponse(String data) {
        if (data.equals("null")) {
            this.closed = true;
            return;
        }
        int buttonID;
        try {
            buttonID = Integer.parseInt(data);
        } catch (Exception e) {
            return;
        }
        if (buttonID >= this.buttons.size()) {
            this.response = new FormNPCResponseStudent(buttonID, null);
            return;
        }
        this.response = new FormNPCResponseStudent(buttonID, buttons.get(buttonID));
    }
    
    public String buttonsToJSON() {
        JSONArray array = new JSONArray();
        for (Button button : buttons.values()) {
            array.put(new JSONObject(button.toJson()));
        }
        return array.toString();
    }
}
