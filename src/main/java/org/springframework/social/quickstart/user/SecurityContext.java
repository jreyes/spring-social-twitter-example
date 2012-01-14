package org.springframework.social.quickstart.user;

public final class SecurityContext
{
// ------------------------------ FIELDS ------------------------------

    private static final ThreadLocal<User> currentUser = new ThreadLocal<User>();

// -------------------------- STATIC METHODS --------------------------

    public static User getCurrentUser()
    {
        User user = currentUser.get();
        if ( user == null )
        {
            throw new IllegalStateException( "No user is currently signed in" );
        }
        return user;
    }

    public static void remove()
    {
        currentUser.remove();
    }

    public static void setCurrentUser( User user )
    {
        currentUser.set( user );
    }

    public static boolean userSignedIn()
    {
        return currentUser.get() != null;
    }
}