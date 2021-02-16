import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
// You are only able to use the org.json library after you've installed it.

import org.json.*;

public class drink_api {

    public static void getDrinkInfo(){
        // Create a HTTP Connection.
        String baseUrl = "https://www.thecocktaildb.com/";
        String callAction = "api/json/v1/";
        String drinkName = "margarita";
        String apiKey = "1/";
        String urlString = baseUrl + callAction + apiKey + "search.php?s=" + drinkName;
        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not find drink: " + status);
            } else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();
                // Print out our complete JSON string.
                System.out.println("Output: " + content.toString());
                // Parse that object into a usable Java JSON object.
                JSONObject obj = new JSONObject(content.toString());
                // Print out the drink recipe.
                String drinkRecipe = obj.getString("strDrink");
                System.out.println("The directions to make  " + drinkName + " are ");
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}
