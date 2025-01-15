package org.utilities;

import io.restassured.path.json.JsonPath;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;

public class VaultUtil {

    private static final String VAULT_URL = "https://your-vault-url/v1/secret/data/"; //API endpoint: URL to fetch secrets
    private static final String VAULT_TOKEN = System.getenv("VAULT_TOKEN"); // Set in environment --Authentication method: API keys, tokens, or certificates.

    public static String getSecret(String secretPath, String key) throws Exception {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(VAULT_URL + secretPath);

        // Add authorization header
        request.addHeader("Authorization", "Bearer " + VAULT_TOKEN);
        HttpResponse response = client.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            String json = EntityUtils.toString(response.getEntity());
            JsonPath jsonPath = new JsonPath(json);

            // Extract the secret value
            return jsonPath.getString(key);
        } else {
            throw new RuntimeException("Failed to retrieve secret: " + response.getStatusLine().getReasonPhrase());
        }
    }
}
