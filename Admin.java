 

import java.util.*;

final public class Admin extends Account
{
    private final String KEY;

    public Admin(String username, String password){
        super(username, password);
        this.KEY = generateRandomKey(20);
    }

    // creates random key when class is initialised
    private String generateRandomKey(int length){
        Random r = new Random ();
        return Long.toString (r.nextLong () & Long.MAX_VALUE, length);
    }

    public String getKey(){
        return this.KEY;
    }
}
