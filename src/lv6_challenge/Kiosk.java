package lv6_challenge;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(List<Menu> menuCategories) {
        List<Menu> categories = new ArrayList<Menu>(menuCategories);

        while (true) {
            // 카테고리 출력
            printCategories(categories);
            // 카테고리 번호 입력받기 : getNumber(제목(카테고리), menu 리스트)
            int selectedCategoryNumber = getNumber("카테고리", categories);
            // 입력된 카테고리 번호 처리
            // 0 입력 시 : while문에서 빠져나오며 프로그램 종료
            if (selectedCategoryNumber == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            // 범위 내 숫자 입력 시
            } else if (selectedCategoryNumber >= 1 || selectedCategoryNumber <= categories.size()) {
                // 해당 번호의 메뉴 출력
                printMenuItems(categories.get(selectedCategoryNumber - 1));
                int order;
                boolean ordering = true;
                while (ordering) {
                    // 메뉴 번호 입력받기 : getNumber(제목(메뉴), menuItem 리스트)
                    int selectedOrderNumber = getNumber("메뉴", categories.get(selectedCategoryNumber - 1).getMenu());
                    // 0 입력 시 : while문 빠져나오며 카테고리 입력으로 되돌아감
                    if (selectedOrderNumber == 0) {
                        System.out.println("MAIN MENU로 돌아갑니다.");
                        try {
                            Thread.sleep(500); // 딜레이 0.5 초
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        ordering = false;
                    // 범위 내 숫자 입력 시
                    } else if (selectedOrderNumber >= 1 || selectedOrderNumber <= categories.get(selectedCategoryNumber - 1).getMenu().size()) {
                        // 주문 내용 프린트
                        System.out.println("주문 내용: " + categories.get(selectedCategoryNumber - 1).getMenu().get(selectedOrderNumber-1));
                        try {
                            Thread.sleep(500); // 딜레이 0.5 초
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        // 메뉴 리스트 재출력 → while문 처음으로 돌아가 메뉴 번호 다시 입력 받기
                        printMenuItems(categories.get(selectedCategoryNumber - 1));
                    }
                }
            }
        }
    }

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

    // getNumber(String, List<?>) : 숫자를 입력하고, 범위 외 숫자 혹은 다른 자료형 예외 처리
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
}
