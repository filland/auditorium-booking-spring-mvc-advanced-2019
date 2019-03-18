package beans.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ProjectWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(AppConfiguration.class);
        webCtx.setServletContext(servletContext);

//        DispatcherServlet springMvc = new DispatcherServlet(webCtx);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", springMvc);
//        servlet.setLoadOnStartup(1);
//        servlet.addMapping("/");
//        servlet.setAsyncSupported(true);

        // bind servlet filter with application context
//        servletContext
//                .addFilter("springSecurityFilterChain", new DelegatingFilterProxy())
//                .addMappingForUrlPatterns(null, true, "/*");

        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(webCtx);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        ServletRegistration.Dynamic dynamic = servletContext.addServlet("spring-ws", messageDispatcherServlet);
        dynamic.addMapping("/soap/*");
        dynamic.setLoadOnStartup(2);
        dynamic.setAsyncSupported(true);
    }
}
