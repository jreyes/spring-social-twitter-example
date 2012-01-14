package org.springframework.social.quickstart.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class JsonDateConverter
{
// ------------------------------ FIELDS ------------------------------

    private static SimpleDateFormat dateFormat =
        new SimpleDateFormat( "EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH );

// -------------------------- STATIC METHODS --------------------------

    public static Date toDate( String dateString )
    {
        if ( dateString == null )
        {
            return null;
        }

        try
        {
            return dateFormat.parse( dateString );
        }
        catch ( ParseException e )
        {
            return null;
        }
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private JsonDateConverter()
    {
    }
}
