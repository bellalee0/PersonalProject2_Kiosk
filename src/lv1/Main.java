package lv1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] menu = new String[]{
                "0. 종료      | 종료",
                "1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거",
                "2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거",
                "3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거",
                "4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거"
        };

        System.out.println("[ SHAKESHACK MENU ]");
        for (String menuItem : menu) {
            System.out.println(menuItem);
        }

        boolean run = true;
        int order;
        while (run) {
            System.out.print("주문 번호를 입력하세요: ");
            try {
                order = scanner.nextInt();

                switch (order) {
                    case 0 -> {
                        System.out.println("프로그램을 종료합니다.");
                        run = false;
                    }
                    case 1, 2, 3, 4 -> System.out.println("주문 내용: " + menu[order]);
                    default -> System.out.println("잘못된 번호입니다. 1부터 " + (menu.length - 1) +"까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                scanner.next();
            }
        }
    }
}