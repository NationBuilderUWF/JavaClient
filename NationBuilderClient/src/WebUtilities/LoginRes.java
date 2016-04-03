package WebUtilities;

import java.io.Serializable;

/**
 * Created by poebr_000 on 4/2/2016.
 */
public class LoginRes implements Serializable{
    public static final long serialVersionUID = 1L;
    public boolean admin;
    public boolean success;
    public int nation;
    public int resources;
}
