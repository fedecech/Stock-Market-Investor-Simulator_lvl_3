 

import java.io.*;

abstract public class Account implements Serializable
{
    private String username;
    private String password;

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean signIn(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            return true;
        }
        return false;
    }
}

