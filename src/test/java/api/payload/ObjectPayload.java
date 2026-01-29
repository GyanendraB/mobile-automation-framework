package api;

import java.util.Map;

public class ObjectPayload {

    private int id;
    private String name;
    private Map<String, Object> data;

    public int getid() {
        return id;
    }
    public void setd() {
        id = this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
