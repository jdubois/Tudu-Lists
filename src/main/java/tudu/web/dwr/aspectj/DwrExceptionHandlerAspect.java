package tudu.web.dwr.aspectj;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.directwebremoting.WebContextFactory;
import org.springframework.orm.ObjectRetrievalFailureException;
import tudu.security.PermissionDeniedException;

import javax.servlet.ServletException;
import java.io.IOException;

@Aspect
public class DwrExceptionHandlerAspect {

    private final Log log = LogFactory.getLog(DwrExceptionHandlerAspect.class);

    /**
     * Render a permission exception JSP fragment.
     */
    @Around("execution(public String tudu.web.dwr.*.*(..))")
    public Object renderRetrievalFailure(ProceedingJoinPoint joinPoint) {
        try {
            return joinPoint.proceed();
        } catch (ObjectRetrievalFailureException orfe) {
            try {
                return WebContextFactory.get().forwardToString(
                        "/WEB-INF/jspf/object_retrieval_failure.jsp");
            } catch (ServletException e) {
                log.error("ServletException : " + e);
                return "";
            } catch (IOException ioe) {
                log.error("IOException : " + ioe);
                return "";
            }
        } catch (PermissionDeniedException pe) {
            try {
                return WebContextFactory.get().forwardToString(
                        "/WEB-INF/jspf/todos_permission_denied.jsp");
            } catch (ServletException e) {
                log.error("ServletException : " + e);
                return "";
            } catch (IOException ioe) {
                log.error("IOException : " + ioe);
                return "";
            }
        } catch (Throwable e) {
            log.error("Exception : " + e);
            return "";
        }
    }

    @Around("execution(public void tudu.web.dwr.*.*(..))")
    public void ignoreRetrievalFailure(ProceedingJoinPoint joinPoint) {
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Exception : " + e);
        }
    }
}
