package service;

import domain.Dorama;
import lombok.extern.log4j.Log4j2;
import repository.DoramaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Log4j2
public class DoramaService {
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        System.out.println("\n1. Search for Dorama");
        System.out.println("2. Save Dorama");
        System.out.println("3. Update Dorama");
        System.out.println("4. Delete Dorama");
        System.out.println("0. Exit");
        System.out.printf("Type the number of the operation: ");
    }

    public static void menuOperation(int operation) {
        switch (operation) {
            case 1 -> findByName(); //Read
            case 2 -> save(); //Create
            case 3 -> updateMenu(); //Update Menu
            case 4 -> delete(); //Delete
            default -> throw new IllegalArgumentException("Invalid operation");
        }
    }

    public static void updateMenu() {
        System.out.println("\n1. Update Dorama Title");
        System.out.println("2. Update Dorama Score");
        System.out.println("3. Update Dorama Release Year");
        System.out.println("4. Back");
        System.out.printf("Type the number of the operation: ");
    }

    public static void updateOperation(int operation) {
        switch (operation) {
            case 1 -> updateTitle();
            case 2 -> updateScore();
            case 3 -> updateReleaseYear();
            default -> throw new IllegalArgumentException("Invalid operation");
        }
    }

    private static void findByName() {
        System.out.printf("\nType the Dorama title you wish to find or Enter to list all Doramas: ");
        String doramaName = sc.nextLine();
        log.info("Searching Dorama");
        List<Dorama> byTitle = DoramaRepository.findByTitle(doramaName);
        byTitle.forEach(d -> System.out.printf("Title: %s | Release Year: %d | Score: %.1f%n"
                , d.getTitle(), d.getReleaseYear(), d.getScore()));
    }

    private static void save() {
        System.out.printf("\nType the Dorama title you wish to add: ");
        String title = sc.nextLine();
        System.out.printf("Release year: ");
        int releaseYear = Integer.parseInt(sc.nextLine());
        System.out.printf("Score of Dorama: ");
        double score = Double.parseDouble(sc.nextLine());
        Dorama dorama = Dorama.builder().title(title).releaseYear(releaseYear).score(score).build();
        log.info("Saving Dorama");
        DoramaRepository.save(dorama);
        log.info("Saved Dorama");
    }


    private static void updateTitle() {
        System.out.printf("\nType the Dorama ID you wish to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Dorama> doramaOptional = DoramaRepository.findById(id);
        if (doramaOptional.isEmpty()) {
            System.out.println("Dorama not found");
            return;
        }
        System.out.printf("\nType the Dorama title: ");
        String title = sc.nextLine();
        Dorama doramaToUpdate = Dorama.builder().id(id).title(title).build();
        log.info("Updating Dorama");
        DoramaRepository.updateTitle(doramaToUpdate);
        log.info("Updated Dorama");
    }

    private static void updateScore() {
        System.out.printf("\nType the Dorama ID you wish to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Dorama> doramaOptional = DoramaRepository.findById(id);
        if (doramaOptional.isEmpty()) {
            System.out.println("Dorama not found");
            return;
        }
        System.out.printf("\nType the Dorama score: ");
        double score = Double.parseDouble(sc.nextLine());
        Dorama doramaToUpdate = Dorama.builder().id(id).score(score).build();
        log.info("Updating Dorama score");
        DoramaRepository.updateScore(doramaToUpdate);
        log.info("Updated Dorama score");
    }

    private static void updateReleaseYear() {
        System.out.printf("\nType the Dorama ID you wish to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Dorama> doramaOptional = DoramaRepository.findById(id);
        if (doramaOptional.isEmpty()) {
            System.out.println("Dorama not found");
            return;
        }
        System.out.printf("\nType the Dorama release year: ");
        int releaseYear = Integer.parseInt(sc.nextLine());
        Dorama doramaToUpdate = Dorama.builder().id(id).releaseYear(releaseYear).build();
        log.info("Updating Dorama release year");
        DoramaRepository.updateReleaseYear(doramaToUpdate);
        log.info("Updated Dorama release year");
    }

    private static void delete() {
        System.out.printf("\nType the Dorama ID you wish to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Dorama> doramaOptional = DoramaRepository.findById(id);
        if (doramaOptional.isEmpty()) {
            System.out.println("Dorama not found");
            return;
        }
        log.info("Deleting Dorama");
        DoramaRepository.delete(doramaOptional.get().getId());
        log.info("Deleted Dorama");
    }
}
