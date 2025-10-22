package lv2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 1; i <= menuItems.size(); i++) {
            System.out.println(i + ". " + menuItems.get(i - 1));
        }
        System.out.println("0. 종료");

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
                    case 1, 2, 3, 4 -> System.out.println("주문 내용: " + menuItems.get(order-1));
                    default -> System.out.println("잘못된 번호입니다. 1부터 4까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                scanner.next();
            }
        }
    }
}