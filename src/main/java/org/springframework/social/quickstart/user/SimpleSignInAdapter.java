package org.springframework.social.quickstart.user;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;

public final class SimpleSignInAdapter
    implements SignInAdapter
{
// ------------------------------ FIELDS ------------------------------

    private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface SignInAdapter ---------------------

    public String signIn( String userId, Connection<?> connection, NativeWebRequest request )
    {
        SecurityContext.setCurrentUser( new User( userId ) );
        userCookieGenerator.addCookie( userId, request.getNativeResponse( HttpServletResponse.class ) );
        return null;
    }
}