package pojos;

import java.util.Map;

public class GoRestPojo {

    private String meta;
    private Map<String, Object> data;

    public String getMeta() {return meta;}
    public void setMeta(String meta) {this.meta = meta;}
    public Map<String, Object> getData() {return data;}
    public void setData(Map<String, Object> data) {this.data = data;}

    @Override
    public String toString() {
        return "GoRestPojo{" +
                "meta='" + meta + '\'' +
                ", data=" + data +
                '}';
    }
}
