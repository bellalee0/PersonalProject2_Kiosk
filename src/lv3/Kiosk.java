package lv3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // 속성
    List<MenuItem> menuItems = new ArrayList<>();

    // 생성자
    Kiosk(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    // 기능
    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 1; i <= menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i - 1));
        }
        System.out.println("0. 종료");

        int order;
        while (true) {
            System.out.print("주문 번호를 입력하세요: ");
            try {
                order = scanner.nextInt();
                if (order == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else if (order >= 1 && order <= menuItems.size()) {
                    System.out.println("주문 내용: " + menuItems.get(order-1));
                } else {
                    System.out.println("잘못된 번호입니다. 1부터 " + menuItems.size() + "까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                scanner.next();
            }
        }
    }
}
