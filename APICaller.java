import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;
import com.google.gson.Gson;

public class APICaller {
    private static String access_key = "99711ccc6e00263172e9f762ef3d851d";
    private static  String baseURL = "http://api.marketstack.com/v1/";
    private HashMap<String, String> options;
    private String endpoint;

    public APICaller(String endpoint) {
        this.endpoint = endpoint;
        this.options = new HashMap<>();
    }

    public APICaller(String endpoint, HashMap<String, String> options) {
        this.endpoint = endpoint;
        this.options = options;
    }

    public <T> ArrayList<T> call(Class<T[]> clazz) throws Exception {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder(
                URI.create(getUrl()))
                .header("accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Map<Map<String, Map<String, String>>, Map<String, ArrayList<Map<String, String>>>> map = gson.fromJson(response.body(), Map.class);
        if(map.containsKey("error")){
            throw new Exception(String.valueOf(map.get("error").get("message")));
        }
        T[] data = gson.fromJson(gson.toJson(map.get("data")), clazz);

        return new ArrayList<T>(Arrays.asList(data));
    }

    public void setUrl(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setUrl(String endpoint, HashMap<String, String> options){
        this.endpoint = endpoint;
        this.options = options;
    }

    public String getUrl() {
        String url = baseURL  + endpoint + "?" + "&access_key=" + access_key;
        if(options.size() > 0){
            for(Map.Entry<String, String> option : options.entrySet()){
                url += "&" + option.getKey() + "=" + option.getValue();
            }
        }
        return url;
    }
}
