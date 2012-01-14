package org.springframework.social.quickstart.user;

public final class User
{
// ------------------------------ FIELDS ------------------------------

    private final String id;

// --------------------------- CONSTRUCTORS ---------------------------

    public User( String id )
    {
        this.id = id;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public String getId()
    {
        return id;
    }
}