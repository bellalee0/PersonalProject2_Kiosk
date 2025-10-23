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

    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(List<Menu> menuCategories) {
        List<Menu> categories = new ArrayList<Menu>(menuCategories);

        Scanner scanner = new Scanner(System.in);
        int selectCategory;
        while (true) {
            printCategories(categories);
            System.out.print("카테고리 번호를 입력하세요: ");
            try {
                selectCategory = scanner.nextInt();
                if (selectCategory == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else if (selectCategory >= 1 && selectCategory <= categories.size()) {
                    printMenuItems(categories.get(selectCategory - 1));
                    int order;
                    boolean ordering = true;
                    while (ordering) {
                        System.out.print("주문 번호를 입력하세요: ");
                        try {
                            order = scanner.nextInt();
                            if (order == 0) {
                                System.out.println("MAIN MENU로 돌아갑니다.");
                                ordering = false;
                            } else if (order >= 1 && order <= categories.get(selectCategory - 1).menuItems.size()) {
                                System.out.println("주문 내용: " + categories.get(selectCategory - 1).menuItems.get(order-1));
                            } else {
                                System.out.println("잘못된 번호입니다. 1부터 " + categories.get(selectCategory - 1).menuItems.size() + "까지의 번호를 입력해주세요.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("숫자만 입력해주세요.");
                            scanner.next();
                        }
                    }
                } else {
                    System.out.println("잘못된 번호입니다. 1부터 " + categories.size() + "까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }
}
