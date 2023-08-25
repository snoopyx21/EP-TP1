import java.lang.Math;
import java.util.*;

public class Evt 
{
    /* type = 0 si départ / type = 1 si arrivée */
    public int type, numero_client;
    /* date_arrive_evt variable supplémentaire pour obtenir la durée d'un evt */
    public double date_actuel, date_arrive_evt;

    public Evt(int type, int numero_client, double date_actuel)
    {
        this.type = type;
        this.numero_client = numero_client;
        this.date_actuel = date_actuel;
        this.date_arrive_evt = 0.0;
    }
    /* surchage de Evt si événement du numéro_client terminé pour afficher sa date d'arrivée */
    public Evt(int type, int numero_client, double date_actuel, double date_arrive_evt)
    {
        this.type = type;
        this.numero_client = numero_client;
        this.date_actuel = date_actuel;
        this.date_arrive_evt = date_arrive_evt;
    }
    public void infos()
    {
        if (type == 0)
            System.out.println("Date=" + date_actuel + "\tDepart  client #" + numero_client + "\tArrive a t=" + 
            date_arrive_evt + "\tDureeEvt : " + getDureeEvt() + "\n");
        else
            System.out.println("Date=" + date_actuel + "\tArrivee client #" + numero_client + "\n");
    }

    public int getType()
    {
        return type;
    }
    public double getDureeEvt()
    {
        return date_actuel - date_arrive_evt;
    }
    public double getDate()
    {
        return date_actuel;
    }
    public int getNumeroClient()
    {
        return numero_client;
    }
}
    
