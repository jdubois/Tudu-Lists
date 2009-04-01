package tudu;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import tudu.domain.model.TodoTest;
import tudu.domain.model.comparator.*;
import tudu.integration.IntegrationTest;
import tudu.security.UserDetailsServiceImplTest;
import tudu.service.impl.ConfigurationManagerImplTest;
import tudu.service.impl.TodoListsManagerImplTest;
import tudu.service.impl.TodosManagerImplTest;
import tudu.service.impl.UserManagerImplTest;
import tudu.web.dwr.impl.TodoListsDwrImplTest;
import tudu.web.dwr.impl.TodosDwrImplTest;
import tudu.web.servlet.BackupServletTest;
import tudu.web.servlet.RssFeedServletTest;

/**
 * Test Suite that run all the project tests.
 *
 * @author Julien Dubois
 */
@RunWith(Suite.class)
@SuiteClasses(value={
        TodoTest.class,
        TodoByAssignedUserAscComparatorTest.class,
        TodoByAssignedUserComparatorTest.class,
        TodoByDescriptionAscComparatorTest.class,
        TodoByDescriptionComparatorTest.class,
        TodoByDueDateAscComparatorTest.class,
        TodoByDueDateComparatorTest.class,
        TodoByPriorityAscComparatorTest.class,
        IntegrationTest.class,
        UserDetailsServiceImplTest.class,
        ConfigurationManagerImplTest.class,
        TodoListsManagerImplTest.class,
        TodosManagerImplTest.class,
        UserManagerImplTest.class,
        TodoListsDwrImplTest.class,
        TodosDwrImplTest.class,
        BackupServletTest.class,
        RssFeedServletTest.class
})
public class AllTests {
}
