package core;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MarKay on 5. 3. 2015.
 */
public class Language {
    private static Locale locale;
    private static ResourceBundle resourceBundle;

    public static void language (String l){
        if (l.equals("en")) locale = new Locale("en");
        if (l.equals("cs")) locale = new Locale("cs");
        if (l.equals("nl")) locale = new Locale("nl");
        resourceBundle = ResourceBundle.getBundle("resources.lang", locale);
    }

    public static void language(){
        resourceBundle = ResourceBundle.getBundle("resources.lang", new Locale("en"));
    }

    public static String getText(String key){
        return resourceBundle.getString(key);
    }

}
