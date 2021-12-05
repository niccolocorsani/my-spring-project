package propertyBasedTest.quickcheck;


import com.example.spring.demo.model.client.Client;
import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(JUnitQuickcheck.class)
public class ClientInputTest {

    @Property
    @Test
    public void testProva(String firstName) throws Exception {
        Client c = new Client();
        c.setFirstName(firstName);
        System.err.println(firstName);
        assertNotNull(c);
    }


    @Property
    @Test
    public void testDate(@From(NumberConstrainGenerator.class) Long id) throws Exception {
        Client c = new Client(id, null, null);
        System.err.println(id);
        assertNotNull(c);
    }


    @Property
    @Test
    public void testName(@From(StringConstrainGenerator.class) String firstName) throws Exception {
        Client c = new Client(null, firstName, null);
        System.err.println(firstName);
        assertNotNull(c);
    }



}

