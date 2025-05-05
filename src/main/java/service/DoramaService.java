package service;

import domain.Dorama;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import repository.DoramaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Log4j2
public class DoramaService {
    private static Scanner sc = new Scanner(System.in);

    public static void menu() {
        System.out.println("Type the number of the operation");
        System.out.println("1. Search for Dorama");
        System.out.println("2. Save Dorama");
        System.out.println("3. Uptate Dorama Title");
        System.out.println("4. Delete Dorama");
        System.out.println("0. Exit");
    }

    public static void menuOperation(int operation) {
        switch (operation) {
            case 1 -> findByName(); //Read
            case 2 -> save(); //Create
            case 3 -> updateTitle(); //Update
            case 4 -> delete(); //delete
            default -> throw new IllegalArgumentException("Invalid operation");
        }
    }

    private static void findByName() {
        System.out.printf("Type the Dorama title you wish to find: ");
        String doramaName = sc.nextLine();
        log.info("Seraching Dorama with name " + doramaName);
        List<Dorama> byTitle = DoramaRepository.findByTitle(doramaName);
        byTitle.forEach(d -> System.out.printf("Dorama found.\nTitle: %s | Release Year: %d | Score: %.1f%n"
                , d.getTitle(), d.getReleaseYear(), d.getScore()));
    }

    private static void save() {
        System.out.printf("Type the Dorama title you wish to add: ");
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
        System.out.printf("Type the Dorama ID you wish to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Dorama> doramaOptional = DoramaRepository.findById(id);
        if (doramaOptional.isEmpty()) {
            System.out.println("Dorama not found");
            return;
        }
        Dorama doramaFromDb = doramaOptional.get();
        System.out.printf("Type the Dorama title: ");
        String title = sc.nextLine();
        Dorama doramaToUpdate = Dorama.builder().id(id).title(title).build();
        log.info("Updating Dorama");
        DoramaRepository.updateTitle(doramaToUpdate);
        log.info("Updated Dorama");
    }

    private static void delete() {
        System.out.printf("Type the Dorama ID you wish to delete: ");
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
