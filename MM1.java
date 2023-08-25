public class MM1
{
    static long duree;
    static int landa, mu, mode_debug;
    static Ech echeancier;
static double rc = 0.0;

    public static void main(String[] args) 
    {
        landa   = Integer.parseInt(args[0]);
        mu      = Integer.parseInt(args[1]);
        duree   = Integer.parseInt(args[2]);
        if (Integer.parseInt(args[3]) == 0 || Integer.parseInt(args[3]) == 1 || Integer.parseInt(args[3]) == 2)
        {
            mode_debug = Integer.parseInt(args[3]);
            echeancier = new Ech(duree, landa, mu, mode_debug);
            rc = echeancier.start_echeancier();
            /* affichage des stats - on envoie la date actuel */
            if (mode_debug != 2) 
                echeancier.stats.affichage(rc, duree, landa, mu);
            else echeancier.stats.affichagedebug2(mode_debug);
        }
    }
}