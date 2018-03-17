package Controllers;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class RequestContext {
    /**
     * Request.
     */
    private HttpServletRequest request;
    /**
     * Config.
     */
    private ServletConfig config;
    /**
     * Context's lock.
     */
    private static ReentrantLock lockContext = new ReentrantLock();
    /**
     * Parameters of the request.
     */
    private Map<String, String[]> requestParameters;
    /**
     * Attributes of the session.
     */
    private Map<String, Object> sessionAttributes;
    /**
     * Attributes of the request.
     */
    private Map<String, Object> requestAttributes;
    /**
     * Parameters of the config.
     */
    private Map<String, String> configParameters;
    /**
     * Parameters of the context.
     */
    private Map<String, String> contextParameters;
    /**
     * Attributes of the context.
     */
    private Map<String, Object> contextAttributes;

    /**
     * The constructor for a request context.
     * @param request request.
     * @param config config.
     */
    public RequestContext(final HttpServletRequest request,
                          final ServletConfig config) {
        this.request = request;
        this.config = config;
        Enumeration<String> names;
        String name;
        HttpSession session = request.getSession(false);
        if (session != null) {
            synchronized (session.getId().intern()) {
                sessionAttributes = new HashMap<>();
                names = session.getAttributeNames();
                while (names.hasMoreElements()) {
                    name = names.nextElement();
                    sessionAttributes.put(name, session.getAttribute(name));
                }
            }
        } else {
            sessionAttributes = new HashMap<>();
        }
        requestParameters = request.getParameterMap();
        names = request.getAttributeNames();
        requestAttributes = new HashMap<>();
        if (names != null) {
            while (names.hasMoreElements()) {
                name = names.nextElement();
                requestAttributes.put(name, request.getAttribute(name));
            }
        }
        configParameters = new HashMap<>();
        if (config != null) {
            names = config.getInitParameterNames();
            if (names != null) {
                while (names.hasMoreElements()) {
                    name = names.nextElement();
                    configParameters.put(name, config.getInitParameter(name));
                }
            }
        }
        contextParameters = new HashMap<>();
        ServletContext context = null;
        if (config != null) {
            context = config.getServletContext();
            names = context.getInitParameterNames();
            if (names != null) {
                while (names.hasMoreElements()) {
                    name = names.nextElement();
                    contextParameters.put(name, context.getInitParameter(name));
                }
            }
            contextAttributes = new HashMap<>();
            lockContext.lock();
            names = context.getAttributeNames();
            if (names != null) {
                while (names.hasMoreElements()) {
                    name = names.nextElement();
                    contextAttributes.put(name, context.getAttribute(name));
                }
            }
            lockContext.unlock();
        }
    }

    /**
     * Returns parameters of the request.
     * @return parameters of the request.
     */
    public Map<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    /**
     * Returns attributes of the session.
     * @return attributes of the session.
     */
    public Map<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    /**
     * Returns attributes of the request.
     * @return attributes of the request.
     */
    public Map<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    /**
     * Returns parameters of the configuration.
     * @return parameters of the configuration.
     */
    public Map<String, String> getConfigParameters() {
        return configParameters;
    }

    /**
     * Returns parameters of the context.
     * @return parameters of the context.
     */
    public Map<String, String> getContextParameters() {
        return contextParameters;
    }

    /**
     * Returns attributes of the context.
     * @return attributes of the context.
     */
    public Map<String, Object> getContextAttributes() {
        return contextAttributes;
    }

    /**
     * Pushes attributes to the session.
     */
    public void pushAttributesToSession() {
        HttpSession session = request.getSession();
        Object ob;
        synchronized (session.getId().intern()) {
            for (Map.Entry<String, Object> entry
                    : sessionAttributes.entrySet()) {
                if (((ob = session.getAttribute(entry.getKey())) != null
                        && !(ob.equals(entry.getValue()))) || (ob == null)) {
                    session.setAttribute(entry.getKey(), entry.getValue());
                }
            }
        }
    }

    /**
     * Pushes attributes to the request.
     */
    public void pushAttributesToRequest() {
        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Pushes attributes to the context.
     */
    public void pushAttributesToServletContext() {
        lockContext.lock();
        for (Map.Entry<String, Object> entry : contextAttributes.entrySet()) {
            config.getServletContext()
                    .setAttribute(entry.getKey(), entry.getValue());
        }
        lockContext.unlock();
    }

    /**
     * Destroys the session.
     */
    public void killSession() {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            sessionAttributes = null;
        }
    }

    /**
     * Removes an attribute from the session.
     * @param name - a name of the attribute.
     */
    public void removeFromSession(final String name) {
        HttpSession session = request.getSession();
        session.removeAttribute(name);
    }
}
