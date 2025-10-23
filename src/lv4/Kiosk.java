package lv4;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // printCategories(List<Menu>) : Menu 객체들의 카테고리 이름 출력
    private void printCategories(List<Menu> categories) {
        System.out.println("[ MAIN MENU ]");
        for (int i = 1; i <= categories.size(); i++) {
            System.out.println(i + ". " + categories.get(i - 1));
        }
        System.out.println("0. 종료");
    }

    // printMenuItems(Menu) : Menu 객체로 받은 MenuItem 출력
    private void printMenuItems(Menu menu) {
        System.out.println("[ " + menu + " MENU ]");
        menu.printMenus();
        System.out.println("0. 뒤로 가기");
    }

    private int getNumber(String name, List<?> nameOfList) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(name + " 번호를 입력하세요: ");
            try {
                int selectedNumber = scanner.nextInt();
                if (selectedNumber < 0 || selectedNumber > nameOfList.size()) { System.out.println("1부터 " + nameOfList.size() + "까지의 번호를 입력해주세요."); }
                else return selectedNumber;
            } catch (InputMismatchException e) {
                System.out.println("1부터 " + nameOfList.size() + "까지의 번호를 입력해주세요.");
                scanner.next();
            }
        }
    }

    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(List<Menu> menuCategories) {
        List<Menu> categories = new ArrayList<Menu>(menuCategories);

        while (true) {
            printCategories(categories);
            int selectedCategoryNumber = getNumber("카테고리", categories);
            if (selectedCategoryNumber == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (selectedCategoryNumber >= 1 || selectedCategoryNumber <= categories.size()) {
                printMenuItems(categories.get(selectedCategoryNumber - 1));
                int order;
                boolean ordering = true;
                while (ordering) {
                    int selectedOrderNumber = getNumber("메뉴", categories.get(selectedCategoryNumber - 1).menuItems);
                    if (selectedOrderNumber == 0) {
                        System.out.println("MAIN MENU로 돌아갑니다.");
                        ordering = false;
                    } else if (selectedOrderNumber >= 1 || selectedOrderNumber <= categories.get(selectedCategoryNumber - 1).menuItems.size()) {
                        System.out.println("주문 내용: " + categories.get(selectedCategoryNumber - 1).menuItems.get(selectedOrderNumber-1));
                        printMenuItems(categories.get(selectedCategoryNumber - 1));
                    }
                }
            }
        }
    }
}
