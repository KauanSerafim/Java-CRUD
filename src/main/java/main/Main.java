package main;

import service.DoramaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== MENU =====");
        Scanner sc = new Scanner(System.in);

        while (true) {
            DoramaService.menu();
            int i = Integer.parseInt(sc.nextLine());
            if (i == 0) break;
            DoramaService.menuOperation(i);
        }
    }
}