package no.hvl.dat107.oblig3.app;

import no.hvl.dat107.oblig3.dao.*;
import no.hvl.dat107.oblig3.model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AnsattDAO ansattDAO = new AnsattDAO();
    private static final AvdelingDAO avdelingDAO = new AvdelingDAO();
    private static final ProsjektDAO prosjektDAO = new ProsjektDAO();
    private static final ProsjektdeltagelseDAO prosjektdeltagelseDAO = new ProsjektdeltagelseDAO();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Hovedmeny ---");
            System.out.println("1. Legg til ansatt");
            System.out.println("2. Hent ansattinfo");
            System.out.println("3. Legg til avdeling");
            System.out.println("4. Hent avdelinginfo");
            System.out.println("5. Legg til prosjekt");
            System.out.println("6. Registrer prosjektdeltagelse");
            System.out.println("7. Føre timer på prosjekt");
            System.out.println("8. Avslutt");
            System.out.print("Velg et alternativ: ");
            int valg = scanner.nextInt();
            scanner.nextLine(); // Håndtere newline

            switch (valg) {
                case 1 -> leggTilAnsatt();
                case 2 -> hentAnsatt();
                case 3 -> leggTilAvdeling();
                case 4 -> hentAvdeling();
                case 5 -> leggTilProsjekt();
                case 6 -> registrerProsjektdeltagelse();
                case 7 -> føreTimer();
                case 8 -> {
                    System.out.println("Avslutter programmet.");
                    return;
                }
                default -> System.out.println("Ugyldig valg. Prøv igjen.");
            }
        }
    }

    private static void leggTilAnsatt() {
        System.out.print("Brukernavn (4 bokstaver): ");
        String brukernavn = scanner.nextLine();
        System.out.print("Fornavn: ");
        String fornavn = scanner.nextLine();
        System.out.print("Etternavn: ");
        String etternavn = scanner.nextLine();
        System.out.print("Stilling: ");
        String stilling = scanner.nextLine();
        System.out.print("Månedslønn: ");
        double månedslønn = scanner.nextDouble();
        System.out.print("Avdeling ID: ");
        int avdelingId = scanner.nextInt();
        scanner.nextLine(); // Håndtere newline

        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(avdelingId);
        if (avdeling == null) {
            System.out.println("Avdeling ikke funnet.");
            return;
        }

        Ansatt ansatt = new Ansatt();
        ansatt.setBrukernavn(brukernavn);
        ansatt.setFornavn(fornavn);
        ansatt.setEtternavn(etternavn);
        ansatt.setDatoAnsettelse(LocalDate.now());
        ansatt.setStilling(stilling);
        ansatt.setMånedslønn(månedslønn);
        ansatt.setAvdeling(avdeling);

        ansattDAO.lagreAnsatt(ansatt);
        System.out.println("Ansatt lagt til!");
    }

    private static void hentAnsatt() {
        System.out.print("Skriv inn ansatt-ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
        if (ansatt != null) {
            System.out.println(ansatt);
        } else {
            System.out.println("Ansatt ikke funnet.");
        }
    }

    private static void leggTilAvdeling() {
        System.out.print("Avdelingsnavn: ");
        String navn = scanner.nextLine();
        System.out.print("Ansatt-ID til sjefen: ");
        int sjefId = scanner.nextInt();
        scanner.nextLine();

        Ansatt sjef = ansattDAO.finnAnsattMedId(sjefId);
        if (sjef == null) {
            System.out.println("Ansatt ikke funnet.");
            return;
        }

        Avdeling avdeling = new Avdeling();
        avdeling.setNavn(navn);
        avdeling.setSjef(sjef);
        avdelingDAO.lagreAvdeling(avdeling);
        System.out.println("Avdeling lagt til!");
    }

    private static void hentAvdeling() {
        System.out.print("Skriv inn avdeling-ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Avdeling avdeling = avdelingDAO.finnAvdelingMedId(id);
        if (avdeling != null) {
            System.out.println(avdeling);
        } else {
            System.out.println("Avdeling ikke funnet.");
        }
    }

    private static void leggTilProsjekt() {
        System.out.print("Prosjektnavn: ");
        String navn = scanner.nextLine();
        System.out.print("Beskrivelse: ");
        String beskrivelse = scanner.nextLine();

        Prosjekt prosjekt = new Prosjekt();
        prosjekt.setNavn(navn);
        prosjekt.setBeskrivelse(beskrivelse);

        prosjektDAO.lagreProsjekt(prosjekt);
        System.out.println("Prosjekt lagt til!");
    }

    private static void registrerProsjektdeltagelse() {
        System.out.print("Ansatt-ID: ");
        int ansattId = scanner.nextInt();
        System.out.print("Prosjekt-ID: ");
        int prosjektId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Rolle: ");
        String rolle = scanner.nextLine();

        Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
        Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);

        if (ansatt == null || prosjekt == null) {
            System.out.println("Ansatt eller prosjekt ikke funnet.");
            return;
        }

        Prosjektdeltagelse prosjektdeltagelse = new Prosjektdeltagelse();
        prosjektdeltagelse.setId(new ProsjektdeltagelseId(ansattId, prosjektId));
        prosjektdeltagelse.setAnsatt(ansatt);
        prosjektdeltagelse.setProsjekt(prosjekt);
        prosjektdeltagelse.setRolle(rolle);
        prosjektdeltagelse.setTimer(0);

        prosjektdeltagelseDAO.lagreProsjektdeltagelse(prosjektdeltagelse);
        System.out.println("Ansatt registrert på prosjekt!");
    }

    private static void føreTimer() {
        System.out.print("Ansatt-ID: ");
        int ansattId = scanner.nextInt();
        System.out.print("Prosjekt-ID: ");
        int prosjektId = scanner.nextInt();
        System.out.print("Antall timer: ");
        int timer = scanner.nextInt();
        scanner.nextLine();

        Prosjektdeltagelse prosjektdeltagelse = prosjektdeltagelseDAO.finnProsjektdeltagelse(ansattId, prosjektId);
        if (prosjektdeltagelse == null) {
            System.out.println("Prosjektdeltagelse ikke funnet.");
            return;
        }

        prosjektdeltagelse.setTimer(prosjektdeltagelse.getTimer() + timer);
        prosjektdeltagelseDAO.oppdaterProsjektdeltagelse(prosjektdeltagelse);

        System.out.println(timer + " timer ført på prosjekt.");
    }
}