package org.springframework.social.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.quickstart.user.SecurityContext;
import org.springframework.social.quickstart.user.SimpleConnectionSignUp;
import org.springframework.social.quickstart.user.SimpleSignInAdapter;
import org.springframework.social.quickstart.user.User;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class SocialConfig
{
// ------------------------------ FIELDS ------------------------------

    @Inject
    private DataSource dataSource;

    @Inject
    private Environment environment;

// -------------------------- OTHER METHODS --------------------------

    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator()
    {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
        registry.addConnectionFactory( new TwitterConnectionFactory( environment.getProperty( "twitter.consumerKey" ),
                                                                     environment.getProperty( "twitter.consumerSecret" ) ) );
        return registry;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository()
    {
        User user = SecurityContext.getCurrentUser();
        return usersConnectionRepository().createConnectionRepository( user.getId() );
    }

    @Bean
    public ProviderSignInController providerSignInController()
    {
        return new ProviderSignInController( connectionFactoryLocator(),
                                             usersConnectionRepository(),
                                             new SimpleSignInAdapter() );
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Twitter twitter()
    {
        Connection<Twitter> twitter = connectionRepository().findPrimaryConnection( Twitter.class );
        return twitter != null ? twitter.getApi() : new TwitterTemplate();
    }

    @Bean
    public UsersConnectionRepository usersConnectionRepository()
    {
        JdbcUsersConnectionRepository repository =
            new JdbcUsersConnectionRepository( dataSource, connectionFactoryLocator(), Encryptors.noOpText() );
        repository.setConnectionSignUp( new SimpleConnectionSignUp() );
        return repository;
    }
}
