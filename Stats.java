public class Stats 
{
    public int numero_client, nombre_client_actuel;
    public int clients_avec_attente, clients_sans_attente;
    public double moyenne_client, temps_moyen_sejour, date_actuel;

    public Stats() 
    {
        this.nombre_client_actuel   = 0;
        this.clients_avec_attente   = 0;
        this.clients_sans_attente   = 0;
        this.numero_client          = 0;
        this.moyenne_client         = 0.0;
        this.temps_moyen_sejour     = 0.0;
        this.date_actuel            = 0.0;
    }

    /* on actualise la date à chaque depart/arrivee, le nombre de clients actuel et la moyenne(esperance) */
    public void maj_date(double date_fin_evt, int evt) 
    {
        this.moyenne_client = this.moyenne_client +
            ( this.nombre_client_actuel * (date_fin_evt - this.date_actuel) );
        this.date_actuel = date_fin_evt;
        if (evt == 0) this.nombre_client_actuel--;
        else this.nombre_client_actuel++;
    }


    public void affichage(double date_total, long duree_simulee, double landa, double mu) 
    {
        double ro;
        
        /* affichage sur le modèle du sujet PDF */
        System.out.println("\n----------------------");
        System.out.println(" RESULTATS THEORIQUES");
        System.out.println("----------------------");
    
        if (landa <= mu) 
        {
            System.out.println(" landa<mu : file stable ");
            ro = landa / mu ; 
        }
        else 
        {
            /* résultats faussés */
            System.out.println(" landa>mu : file non stable ");
            ro = mu / landa ;
        }
        System.out.println(" ro (landa/mu) = " + ro);
        System.out.println(" nombre de clients attendus (landa x duree) = " + landa * duree_simulee);
        System.out.println(" Prob de service sans attente (1 - ro) = " + (1 - ro));
        System.out.println(" Prob file occupee (ro) = " + ro);
        System.out.println(" Debit (landa) = " + landa);
        System.out.println(" Esp nb clients (ro/1-ro) = " + (ro / ( 1 - ro )) );
        System.out.println(" Temps moyen de sejour (1/mu(1-ro)) = " + (1 / ( mu * (1 - ro) ) ));

        System.out.println("\n----------------------");
        
        System.out.println(" RESULTATS SIMULATION");
        System.out.println("----------------------");
        System.out.println(" Nombre total de clients = " + (this.numero_client - 1));
        System.out.println(" Proportion clients sans attente = " + (double)clients_sans_attente / (numero_client - 1));
        System.out.println(" Proportion clients avec attente = " + (double)clients_avec_attente / (numero_client - 1));
        System.out.println(" Debit = " + (double)(this.numero_client - 1) / date_total);
        System.out.println(" Nb moyen de clients dans systeme = " + this.moyenne_client / date_total);
        System.out.println(" Temps moyen de sejour = " + this.temps_moyen_sejour / (numero_client - 1));
        System.out.println("\n");
    }

    /* debug = 2 pour l'affichage dans res.dat pour gnuplot */
    public void affichagedebug2(double date_total)
    {
        System.out.printf("%d %f %f %f %f %f %f", (this.numero_client - 1),
            (double)clients_sans_attente / (numero_client - 1),
            (double)clients_avec_attente / (numero_client - 1),
            (double)(this.numero_client - 1) / date_total,
            this.moyenne_client / date_total,
            this.temps_moyen_sejour / (numero_client - 1),
            date_total);
    }
}