package org.springframework.social.quickstart;

import org.springframework.social.quickstart.model.Retweet;
import org.springframework.social.quickstart.model.RetweetList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
public class TwitterController
{
// ------------------------------ FIELDS ------------------------------

    private final Twitter twitter;

// --------------------------- CONSTRUCTORS ---------------------------

    @Inject
    public TwitterController( Twitter twitter )
    {
        this.twitter = twitter;
    }

// -------------------------- OTHER METHODS --------------------------

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home( Model model )
    {
        List<Retweet> retweets = twitter.restOperations().getForObject(
            "https://api.twitter.com/1/statuses/retweets_of_me.json",
            RetweetList.class );
        model.addAttribute( "retweets", retweets );
        return "home";
    }
}
