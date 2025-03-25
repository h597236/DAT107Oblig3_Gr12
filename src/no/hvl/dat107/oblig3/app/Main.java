package no.hvl.dat107.oblig3.app;

import no.hvl.dat107.oblig3.dao.*;
import no.hvl.dat107.oblig3.model.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();
        ProsjektDAO prosjektDAO = new ProsjektDAO();

        while (true) {
            System.out.println("1. Legg til ansatt");
            System.out.println("2. Hent ansatt");
            System.out.println("3. Legg til avdeling");
            System.out.println("4. Legg til prosjekt");
            System.out.println("5. Registrer prosjektdeltagelse");
            System.out.println("6. Avslutt");
            int valg = scanner.nextInt();

            if (valg == 1) {
                // Legg til ansatt (fyll inn detaljer fra brukerinput)
                Ansatt ansatt = new Ansatt();
                // sett verdiene for ansatt
                ansattDAO.lagreAnsatt(ansatt);
            } else if (valg == 2) {
                // Hent ansatt basert på ID eller brukernavn
                System.out.print("Skriv inn ansatt-ID: ");
                int id = scanner.nextInt();
                Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
                System.out.println(ansatt);
            } else if (valg == 3) {
                // Legg til ny avdeling
                Avdeling avdeling = new Avdeling();
                // sett detaljer for avdeling
                avdelingDAO.lagreAvdeling(avdeling);
            } else if (valg == 4) {
                // Legg til nytt prosjekt
                Prosjekt prosjekt = new Prosjekt();
                // sett detaljer for prosjekt
                prosjektDAO.lagreProsjekt(prosjekt);
            } else if (valg == 5) {
                // Registrer prosjektdeltagelse
                // Fyll inn logikk for å registrere deltagelse og timer
            } else {
                break;
            }
        }
    }
}