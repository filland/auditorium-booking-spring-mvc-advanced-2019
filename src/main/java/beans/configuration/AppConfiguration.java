package beans.configuration;

import beans.configuration.db.DataSourceConfiguration;
import beans.configuration.db.DbSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Created with IntelliJ IDEA.
 * User: Dmytro_Babichev
 * Date: 21/2/16
 * Time: 1:12 PM
 */
@Configuration
@EnableWebMvc
@ComponentScan("beans")
//@Import({AuditoriumConfiguration.class,
//        StrategiesConfiguration.class,
//        DataSourceConfiguration.class,
//        DbSessionFactory.class})
@EnableAspectJAutoProxy
public class AppConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("/WEB-INF/freemarker/");
        resolver.setSuffix(".ftl");
        registry.viewResolver(resolver);
    }

    @Bean
    public FreeMarkerConfig getFreeMarkerConfig() {

        FreeMarkerConfigurer config = new FreeMarkerConfigurer();
        // TODO have no idea why empty string makes freeMarkerViewResolver work
        config.setTemplateLoaderPath("");
        return config;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
