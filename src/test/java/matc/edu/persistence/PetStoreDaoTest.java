package matc.edu.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.apache.logging.log4j.*;

class PetStoreDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Test
    void getPetTest() {
        PetStoreDao dao = new PetStoreDao();
        assertEquals("Noya", dao.getPet(2).getName());
    }
}