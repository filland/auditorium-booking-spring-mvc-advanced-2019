package beans.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class WebSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public WebSecurityInitializer() {
        super(SecurityConfiguration.class);
    }
}
