import com.fasterxml.jackson.databind.ObjectMapper;
import com.swapi.Planet;
import org.junit.Test;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceClient {

    @Test
    public void testswapiJSON() throws Exception {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://swapi.info/api/planets/1");
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        //use Jackson ObjectMapper to convert JSON response to objects
        ObjectMapper mapper = new ObjectMapper();
        Planet planet = mapper.readValue(response, Planet.class);
        String expectedPlanetClimate = "arid";
        assertEquals(expectedPlanetClimate, planet.getClimate());
    }
}