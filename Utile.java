import java.lang.Math;
import java.util.*;

public class Utile
{
    public static double loiExponentielle(double date, double parametre) 
    {
        /* tirage aleatoire - double entre 0 et 1 */
        double r = Math.random();
        double result =  (date + ( ( (-Math.log(1 - r)) ) / parametre));
        return result;
    }
        
    public static LinkedList<Evt> ajoutEvt(Evt e, LinkedList<Evt> list) 
    {
        int i ;
        if(list.size() == 0)
        {
            list.add(e);
            return list;
        }
        for( i = 1 ; i <= list.size() ; i++)
        {
            if (i == list.size() )
            {
                list.add(e);
                break;
            }
            if(list.get(i).getDate() > e.getDate())
            {
                list.add(i,e);
                break;
            }
            
        }
        return list;
    }
    

}