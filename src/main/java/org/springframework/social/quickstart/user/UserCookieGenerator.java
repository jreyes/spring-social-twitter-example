package org.springframework.social.quickstart.user;

import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class UserCookieGenerator
{
// ------------------------------ FIELDS ------------------------------

    private final CookieGenerator userCookieGenerator = new CookieGenerator();

// --------------------------- CONSTRUCTORS ---------------------------

    public UserCookieGenerator()
    {
        userCookieGenerator.setCookieName( "quickstart_user" );
    }

// -------------------------- OTHER METHODS --------------------------

    public void addCookie( String userId, HttpServletResponse response )
    {
        userCookieGenerator.addCookie( response, userId );
    }

    public String readCookieValue( HttpServletRequest request )
    {
        Cookie[] cookies = request.getCookies();
        if ( cookies == null )
        {
            return null;
        }
        for ( Cookie cookie : cookies )
        {
            if ( cookie.getName().equals( userCookieGenerator.getCookieName() ) )
            {
                return cookie.getValue();
            }
        }
        return null;
    }

    public void removeCookie( HttpServletResponse response )
    {
        userCookieGenerator.addCookie( response, "" );
    }
}