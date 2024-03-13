package matc.edu.persistence;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import petstore.swagger.Pet;

import org.apache.logging.log4j.*;
import util.PropertiesLoader;
import java.util.*;

public class PetStoreDao implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    Properties properties = loadProperties("/api.properties");

    public Pet getPet(int id) {
        Pet pet;
        Client client = ClientBuilder.newClient();
        String idString = Integer.toString(id);
        WebTarget target =
                client.target(properties.getProperty("swagger.getpet.url") + idString);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            pet = mapper.readValue(response, Pet.class);
        } catch (JsonProcessingException e) {
            logger.error("There was a Json processing exception in getPet()");
            throw new RuntimeException(e);
        }
        return pet;
    }
}
