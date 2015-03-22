import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.*;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpResponse;

public class SensorClient {
	static final String M2X_BASE_URL = "https://api-m2x.att.com/v2";

	static final String M2X_MASTER_KEY = "a09bbcb3873e61a7439b53107aa81af1";
	static final String M2X_DEVICE_ID = "6875eb1f1c193c46f9c20fbdd915d84e";
	static final String M2X_STREAM_NAME = "ultrasonic-sensor-1";

	public static void main(String[] args) {
		try {
			// Create a put request to /devices/{id}/streams/{name}/value
			DefaultHttpClient httpClient = new DefaultHttpClient();

			HttpPut putRequest = new HttpPut(
				String.format(
					"%s/devices/%s/streams/%s/value",
					M2X_BASE_URL,
					M2X_DEVICE_ID,
					M2X_STREAM_NAME
				)
			);
			//System.out.println("putREQ:  "+putRequest);

			putRequest.setHeader("X-M2X-KEY", M2X_MASTER_KEY);

			StringEntity input = new StringEntity(
				String.format(
					"{\"value\": %s}",
					Math.floor(Math.random() * 100)
				)
			);
			input.setContentType("application/json");

			putRequest.setEntity(input);
			HttpResponse response = httpClient.execute(putRequest);

			System.out.println(response);
			httpClient.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
