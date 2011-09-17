package tudu.service.impl;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import tudu.domain.Property;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.easymock.EasyMock.*;

public class ConfigurationServiceImplTest {

    ConfigurationServiceImpl configurationService = new ConfigurationServiceImpl();

    EntityManager em;

    @Before
    public void before() {
        em = createMock(EntityManager.class);
        ReflectionTestUtils.setField(configurationService, "em", em);
    }

    @After
    public void after() {
        verify(em);
    }

    private void replay() {
        EasyMock.replay(em);
    }

    @Test
    public void testGetProperty() {
        Property property = new Property();
        property.setKey("key");
        property.setValue("value");
        expect(em.find(Property.class, "key")).andReturn(property);

        replay();
        Property test = configurationService.getProperty("key");
        assertEquals("value", test.getValue());
    }

    @Test
    public void testUpdateEmailProperties() {
        Property hostProperty = new Property();
        hostProperty.setKey("smtp.host");
        hostProperty.setValue("value");
        expect(em.find(Property.class, "smtp.host")).andReturn(hostProperty);

        Property portProperty = new Property();
        portProperty.setKey("smtp.port");
        portProperty.setValue("value");
        expect(em.find(Property.class, "smtp.port")).andReturn(portProperty);

        Property userProperty = new Property();
        userProperty.setKey("smtp.user");
        userProperty.setValue("value");
        expect(em.find(Property.class, "smtp.user")).andReturn(userProperty);

        Property passwordProperty = new Property();
        passwordProperty.setKey("smtp.password");
        passwordProperty.setValue("value");
        expect(em.find(Property.class, "smtp.password")).andReturn(passwordProperty);

        Property fromProperty = new Property();
        fromProperty.setKey("smtp.host");
        fromProperty.setValue("value");
        expect(em.find(Property.class, "smtp.from")).andReturn(fromProperty);

        replay();

        configurationService.updateEmailProperties("host", "port", "user",
                "password", "from");
        assertEquals("host", hostProperty.getValue());
        assertEquals("port", portProperty.getValue());
        assertEquals("user", userProperty.getValue());
        assertEquals("password", passwordProperty.getValue());
        assertEquals("from", fromProperty.getValue());
    }

}
