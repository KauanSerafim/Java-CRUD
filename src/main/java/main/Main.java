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
            if (i == 3) {
                while (true) {
                    int j = Integer.parseInt(sc.nextLine());
                    if (j == 4) break;
                    DoramaService.updateOperation(j);
                }
            }
        }
    }
}