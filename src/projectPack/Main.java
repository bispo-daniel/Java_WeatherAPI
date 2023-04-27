package projectPack;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JsonOrgJsonProvider;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Main {
	static OkHttpClient client = new OkHttpClient();
	
	static ArrayList<String> getJSON(String url) throws IOException {
		
		Request req = new Request.Builder().url(url).build();
		Response res = client.newCall(req).execute();	
		
		ArrayList<String> jsonValues = new ArrayList<>();
		
		if(res.code() == 200){
			String jsonString = res.body().string();
			
			JSONObject obj = new JSONObject(jsonString);
			
			Configuration config = Configuration.builder()
					.jsonProvider(new JsonOrgJsonProvider())
					.build();
							
			JsonPath jsonPathLocationName = JsonPath.compile("$.location.name");
			Object objectLocationName= jsonPathLocationName.read(obj, config);
			
			JsonPath jsonPathLocationRegion = JsonPath.compile("$.location.region");
			Object objectLocationRegion= jsonPathLocationRegion.read(obj, config);
			
			JsonPath jsonPathLocationCountry = JsonPath.compile("$.location.country");
			Object objectLocationCountry= jsonPathLocationCountry.read(obj, config);
			
			JsonPath jsonPathCurrentConditionIcon = JsonPath.compile("$.current.condition.icon");
			Object objectCurrentConditionIcon = jsonPathCurrentConditionIcon.read(obj, config);
			
			JsonPath jsonPathCurrentConditionText = JsonPath.compile("$.current.condition.text");
			Object objectCurrentConditionText = jsonPathCurrentConditionText.read(obj, config);
			
			JsonPath jsonPathCurrentTemp = JsonPath.compile("$.current.temp_c");
			Object objectCurrentTemp = jsonPathCurrentTemp.read(obj, config);
			
			JsonPath jsonPathCurrentWind = JsonPath.compile("$.current.wind_kph");
			Object objectCurrentWind = jsonPathCurrentWind.read(obj, config);
			
			jsonValues.add(objectLocationName.toString());
			jsonValues.add(objectLocationRegion.toString());
			jsonValues.add(objectLocationCountry.toString());
			
			jsonValues.add(objectCurrentConditionIcon.toString());
			jsonValues.add(objectCurrentConditionText.toString());
			jsonValues.add(objectCurrentTemp.toString());
			jsonValues.add(objectCurrentWind.toString());
		
		} else {
			JOptionPane.showMessageDialog(null, "Something different happened...\nTry again!");
			main(null);
		}
		
		return jsonValues;
	}

	 static void menu() throws IOException {
		String menuDialog = "Hello, This is the Java Weather App!!\n\n Type a city name to display it's weather \n Type 0 to exit";
	 	String query = JOptionPane.showInputDialog(null, menuDialog);

		if("0".equals(query)){
			System.exit(0);
		} else {
			MySecretURL urlWithKey = new MySecretURL();
			String url = urlWithKey.URL;
			String formatedURL = String.format(url, query);

			ArrayList<String> response = getJSON(formatedURL);

			String queryDialog = " %s - %s - %s \n %s - %s°C - Wind: %s KM/H";
			String formatedDialog = String.format(queryDialog, response.get(0), response.get(1), response.get(2), response.get(4), response.get(5), response.get(6));
			JOptionPane.showMessageDialog(null, formatedDialog,
			        "Weather App",
			        JOptionPane.INFORMATION_MESSAGE);

		}

		menu();
	}

	public static void main(String[] args) throws IOException {
		menu();
	}
}