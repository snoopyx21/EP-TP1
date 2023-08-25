import java.util.*;

public class Ech
{
    public LinkedList<Evt> echeancier;
    public Stats stats;
    public long duree_simule;
    public int mode_debug;
    /* parametre pour loi expo */
    public double mu, landa;


    public Ech(long duree_simule, double landa, double mu, int mode_debug)
    {
        /* initialisation du premier evenement */
        Evt premier_evt = new Evt( 1, 0, 0.0 );
        this.echeancier = new LinkedList<Evt>();
        this.echeancier = Utile.ajoutEvt(premier_evt, this.echeancier);

        this.duree_simule = duree_simule;
        this.mu = mu;
        this.landa = landa;
        this.mode_debug = mode_debug;
        /* invocation de l'affichage */
        this.stats = new Stats();
        stats.numero_client++;
    }

    public double start_echeancier() 
    {
        /* cas pour le premier client */
        double date_arrivee; // date t de l'arrive de l'evt
        double date_depart; // date t de depart de l'evt = fin
        double date_arrivee_n1; // date t de l'arrive de l'evt n+1 que l'on peut prevoir

        Evt evenement = echeancier.getFirst();

        /* date_arrive est la date d'arrive du premier evt */
        date_arrivee = evenement.getDate(); 
        stats.date_actuel = date_arrivee;

        /* loi expo pour définir quand le prochain client va arrivé */ 
        date_arrivee_n1 = Utile.loiExponentielle(date_arrivee, landa);
        date_depart = Utile.loiExponentielle(date_arrivee, mu);
        
        /* départ du premier client */
        Evt evt_de_depart = new Evt(0, stats.numero_client, date_depart, date_arrivee);
        this.echeancier = Utile.ajoutEvt(evt_de_depart, echeancier);
        
        /* arrivée du prochain client */
        stats.numero_client++;
        Evt arrivee_prochain_evt = new Evt(1, stats.numero_client, date_arrivee_n1);
        this.echeancier = Utile.ajoutEvt(arrivee_prochain_evt, echeancier);
        
        stats.nombre_client_actuel++;
        stats.clients_sans_attente++;
        stats.temps_moyen_sejour += date_depart - date_arrivee;
        
        echeancier.removeFirst();
        
        /* tant que le temps en entrée n'a pas été atteint */
        while (echeancier.size() != 0) 
        {
            /* tête de l'écheancier */
            evenement = echeancier.getFirst();
           
            /* si l'evt est un départ */
            if (evenement.getType() == 0) 
            {
                stats.maj_date(evenement.getDate(), 0);
                if (mode_debug == 1) evenement.infos();
                echeancier.removeFirst();
            } 
            /* si l'evt est une arrivée */
            else
            {
                date_arrivee = date_arrivee_n1;
                date_arrivee_n1 = Utile.loiExponentielle(date_arrivee, landa);
                /* on vérifie que le client devant le client qui vient d'arriver est encore là ou pas */
                date_depart = verificationClient(date_depart, date_arrivee);
                
                /* départ du client qu vient d'arriver à placer dans l'écheancier */
                evt_de_depart = new Evt(0, stats.numero_client, date_depart, date_arrivee);
                this.echeancier = Utile.ajoutEvt(evt_de_depart, echeancier);
                
                /* arrivé du prochain client à placer dans l'écheancier */
                if (date_arrivee_n1 < duree_simule) 
                {
                    arrivee_prochain_evt = new Evt(1, stats.numero_client, date_arrivee_n1); 
                    this.echeancier = Utile.ajoutEvt(arrivee_prochain_evt, echeancier);
                    stats.maj_date(evenement.getDate(), 1);
                }

                stats.numero_client++;
                stats.temps_moyen_sejour += date_depart - date_arrivee;
                if (mode_debug == 1) evenement.infos();
                
                echeancier.removeFirst();
                
            }/* else */
        }/* while */
        return evenement.getDate();
    }

    double verificationClient(double date_depart, double date_arrivee)
    {
        if (date_depart > date_arrivee) 
        {
            stats.clients_avec_attente++;
            date_depart = Utile.loiExponentielle(date_depart, this.mu);
        }
        else 
        {
            stats.clients_sans_attente++;
            date_depart = Utile.loiExponentielle(date_arrivee, this.mu);
        }
        return date_depart;
    }
}