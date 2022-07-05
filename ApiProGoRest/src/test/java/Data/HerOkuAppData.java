package Data;

import java.util.HashMap;
import java.util.Map;

public class HerOkuAppData {

    public Map<String,Object> dataKeyMap(String firstname, String lastname, int totalprice, boolean depositpaid){

        Map<String,Object> dataKeyMap = new HashMap<>();
        dataKeyMap.put("firstname",firstname);
        dataKeyMap.put("lastname",lastname);
        dataKeyMap.put("totalprice",totalprice);
        dataKeyMap.put("depositpaid",depositpaid);

        return dataKeyMap;
    }

    public Map<String,Object> dataMap(String checkin, String checkout){

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("checkin",checkin);
        dataMap.put("checkout",checkout);

        return dataMap;
    }

















}
