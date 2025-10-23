package lv4;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(Menu menu) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        menu.printMenus();
        System.out.println("0. 종료");

        int order;
        while (true) {
            System.out.print("주문 번호를 입력하세요: ");
            try {
                order = scanner.nextInt();
                if (order == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else if (order >= 1 && order <= menu.menuItems.size()) {
                    System.out.println("주문 내용: " + menu.menuItems.get(order-1));
                } else {
                    System.out.println("잘못된 번호입니다. 1부터 " + menu.menuItems.size() + "까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                scanner.next();
            }
        }
    }
}
