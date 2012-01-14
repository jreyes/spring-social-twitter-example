package org.springframework.social.quickstart.user;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple little {@link ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 *
 * @author Keith Donald
 */
public final class SimpleConnectionSignUp
    implements ConnectionSignUp
{
// ------------------------------ FIELDS ------------------------------

    private final AtomicLong userIdSequence = new AtomicLong();

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface ConnectionSignUp ---------------------

    public String execute( Connection<?> connection )
    {
        return Long.toString( userIdSequence.incrementAndGet() );
    }
}
