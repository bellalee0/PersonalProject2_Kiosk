package lv7_challenge;

import java.util.List;

// 화면에 출력하는 메서드들 모아두는 용도
public class Display {
    // printCategories(List<Menu>, Cart) : Menu 객체들의 카테고리 이름 출력, Cart가 비어있지 않으면 [Order Menu]도 함께 출력
    int printCategories(List<Menu> categories, Cart cart) {
        System.out.println("[ MAIN MENU ]");
        int i = 0;
        for (Menu menu : categories) {
            System.out.println(++i + ". " + menu);
        }
        System.out.println("0. 종료");
        if (!cart.isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println(++i + ". Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println(++i + ". Cancel     | 진행 중인 주문을 취소합니다.");
        }
        return i;
    }

    // printMenuItems(Menu) : Menu 객체로 받은 MenuItem 출력
    void printMenuItems(Menu menu) {
        System.out.println("[ " + menu + " MENU ]");
        menu.printMenus();
        System.out.println("0. 뒤로 가기");
    }

    // printCartList(Cart) : 장바구니 내 메뉴 개수와 메뉴 리스트, 총액 출력
    void printCartList(Cart cart) {
        System.out.println("[ Orders(" + cart.getCartItems().size() + ") ]");
        cart.printCartItems();
        System.out.println("[ Total ]");
        System.out.println("W " + cart.getTotalPrice());
    }

    void printUserType() {
        System.out.println("할인 정보를 입력해주세요.");
        System.out.println("1. 국가유공자  : 10%\n" +
                "2. 군인      : 5%\n" +
                "3. 학생      : 3%\n" +
                "4. 일반      : 0%");
    }
}
