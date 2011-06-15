/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package presenca;

/**
 *
 * @author drubin
 */
public class Utils {
    public static String titlelize(String str){
        return str.substring(0, 1).toUpperCase().
                   concat(str.substring(1));
    }
    
    public static String chop(String str){
        return str.substring(0, str.length()-1);
    }
    
}
