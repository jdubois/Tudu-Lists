package tudu.service.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tudu.domain.Property;

import static org.junit.Assert.assertEquals;

public class ConfigurationServiceImplTest {

    ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl();

    @Before
    public void before() {
        //propertyDAO = createMock(PropertyDAO.class);
        //ReflectionTestUtils.setField(configurationService, "propertyDAO", propertyDAO);
    }

    @After
    public void after() {
        //verify(propertyDAO);
    }

    @Test
    public void testGetProperty() {
        Property property = new Property();
        property.setKey("key");
        property.setValue("value");
        //expect(propertyDAO.getProperty("key")).andReturn(property);

        //replay(propertyDAO);
        Property test = configurationService.getProperty("key");
        assertEquals("value", test.getValue());
    }

    @Test
    public void testUpdateEmailProperties() {
        Property hostProperty = new Property();
        hostProperty.setKey("smtp.host");
        hostProperty.setValue("value");
        //expect(propertyDAO.getProperty("smtp.host")).andReturn(hostProperty);
        //propertyDAO.updateProperty(hostProperty);

        Property portProperty = new Property();
        portProperty.setKey("smtp.port");
        portProperty.setValue("value");
        //expect(propertyDAO.getProperty("smtp.port")).andReturn(portProperty);
        //propertyDAO.updateProperty(portProperty);

        Property userProperty = new Property();
        userProperty.setKey("smtp.user");
        userProperty.setValue("value");
        //expect(propertyDAO.getProperty("smtp.user")).andReturn(userProperty);
        //propertyDAO.updateProperty(userProperty);

        Property passwordProperty = new Property();
        passwordProperty.setKey("smtp.password");
        passwordProperty.setValue("value");
        //expect(propertyDAO.getProperty("smtp.password")).andReturn(
        //        passwordProperty);
        //propertyDAO.updateProperty(passwordProperty);

        Property fromProperty = new Property();
        fromProperty.setKey("smtp.host");
        fromProperty.setValue("value");
        //expect(propertyDAO.getProperty("smtp.from")).andReturn(fromProperty);
        //propertyDAO.updateProperty(fromProperty);

        //replay(propertyDAO);
        configurationService.updateEmailProperties("host", "port", "user",
                "password", "from");
        assertEquals("host", hostProperty.getValue());
        assertEquals("port", portProperty.getValue());
        assertEquals("user", userProperty.getValue());
        assertEquals("password", passwordProperty.getValue());
        assertEquals("from", fromProperty.getValue());
    }

}
