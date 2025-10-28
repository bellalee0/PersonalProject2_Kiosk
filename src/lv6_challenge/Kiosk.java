package lv6_challenge;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(List<Menu> menuCategories) {
        List<Menu> categories = new ArrayList<Menu>(menuCategories);
        // Cart 객체 생성
        Cart cart = new Cart();
        // execution : start() 메서드 진행 여부 (false가 될 시, start() 메서드 종료)
        boolean execution = true;

        while (execution) {
            // 카테고리 출력
            int categoryRange = printCategories(categories, cart);
            // 카테고리 번호 입력받기 : getNumber(제목(카테고리), menu 리스트)
            int selectedCategoryNumber = getNumber("카테고리", categoryRange);
            // 입력된 카테고리 번호 처리
            // 0 입력 시 : while문에서 빠져나오며 프로그램 종료
            if (selectedCategoryNumber == 0) {
                System.out.println("프로그램을 종료합니다.");
                execution = false;
            } else if (selectedCategoryNumber >= 1 && selectedCategoryNumber <= categories.size()) {
                // 범위 내 숫자 입력 시
                boolean ordering = true;
                while (ordering) {
                    // 해당 번호의 메뉴 출력
                    printMenuItems(categories.get(selectedCategoryNumber - 1));
                    // 메뉴 번호 입력받기 : getNumber(제목(메뉴), menuItem 리스트)
                    int selectedOrderNumber = getNumber("메뉴", categories.get(selectedCategoryNumber - 1).getMenu().size());
                    // 0(뒤로 가기) 입력 시 : while문 빠져나오며 카테고리 입력으로 되돌아감
                    if (selectedOrderNumber == 0) {
                        System.out.println("MAIN MENU로 돌아갑니다.");
                        delay(300);
                        ordering = false;
                    } else if (selectedOrderNumber >= 1 || selectedOrderNumber <= categories.get(selectedCategoryNumber - 1).getMenu().size()) {
                        // 범위 내 숫자 입력 시
                        // 주문 내용 프린트
                        System.out.println("주문 내용: " + categories.get(selectedCategoryNumber - 1).getMenu().get(selectedOrderNumber-1));
                        delay(500);
                        System.out.println("위 내용을 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인      | 2. 취소");
                        int checkAddingToCart = checkYesOrNo();
                        if (checkAddingToCart == 1) {
                            // 장바구니에 추가 O
                            cart.saveItem(categories.get(selectedCategoryNumber - 1).getMenu().get(selectedOrderNumber-1));
                            cart.printCartItems();
                            System.out.println("장바구니에 추가되었습니다.");
                            System.out.println("MAIN MENU로 돌아갑니다.");
                            delay(500);
                            ordering = false;
                        } else if (checkAddingToCart == 2) {
                            // 장바구니에 추가 X
                            System.out.println("주문을 취소합니다.");
                            System.out.println("MAIN MENU로 돌아갑니다.");
                            delay(500);
                            ordering = false;
                        }
                    }
                }
            } else if (selectedCategoryNumber == categories.size()+1) {
                // [Order Menu]의 Orders 선택 시
                System.out.println("[ Orders(" + cart.getCartItems().size() + ") ]");
                cart.printCartItems();
                System.out.println("[ Total ]");
                System.out.println("W " + cart.getTotalPrice());
                System.out.println("주문하시겠습니까?");
                System.out.println("1. 확인      | 2. 취소 (장바구니 초기화)");
                int checkAddingToCart = checkYesOrNo();
                if (checkAddingToCart == 1) {
                    // 주문 O : 최종 금액 출력 후 프로세스 종료
                    System.out.println("주문이 완료되었습니다. 총 " + cart.getCartItems().size() + "개 메뉴, 금액은 W " + cart.getTotalPrice() + " 입니다.");
                    System.out.println("프로그램이 종료됩니다.");
                    execution = false;
                } else if (checkAddingToCart == 2) {
                    // 주문 X : 장바구니 초기화
                    cart.clearCart();
                    System.out.println("장바구니가 초기화되었습니다. 주문을 다시 시작합니다.");
                    delay(500);
                }
            } else if (selectedCategoryNumber == categories.size()+2) {
                // [Order Menu]의 Cancel 선택 시
                System.out.println("원하는 작업을 선택해주세요.");
                System.out.println("1. 전체 삭제    | 2. 특정 주문만 삭제");
                int checkRemoveRange = checkYesOrNo();
                if (checkRemoveRange == 1) {
                    // 전체 삭제 : 장바구니 초기화
                    cart.clearCart();
                    System.out.println("장바구니가 초기화되었습니다. 주문을 다시 시작합니다.");
                    delay(500);
                } else if (checkRemoveRange == 2) {
                    // 특정 주문만 삭제 : 원하는 번호 받은 뒤, 해당 메뉴 삭제
                    cart.printCartItems();
                    int removeIndex = getNumber("삭제하고 싶은 메뉴의", cart.getCartItems().size());
                    cart.removeItem(removeIndex-1);
                    delay(300);
                    cart.printCartItems();
                    System.out.println("선택한 메뉴가 삭제되었습니다. 주문을 다시 시작합니다.");
                    delay(500);
                }
            }
        }
    }

    // printCategories(List<Menu>, Cart) : Menu 객체들의 카테고리 이름 출력, Cart가 비어있지 않으면 [Order Menu]도 함께 출력
    private int printCategories(List<Menu> categories, Cart cart) {
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
    private void printMenuItems(Menu menu) {
        System.out.println("[ " + menu + " MENU ]");
        menu.printMenus();
        System.out.println("0. 뒤로 가기");
    }

    // getNumber(String, int) : 숫자를 입력하고, 범위 외 숫자 혹은 다른 자료형 예외 처리
    private int getNumber(String name, int maximumRange) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(name + " 번호를 입력하세요: ");
            try {
                int selectedNumber = scanner.nextInt();
                if (selectedNumber < 0 || selectedNumber > maximumRange) { System.out.println("1부터 " + maximumRange + "까지의 번호를 입력해주세요."); }
                else return selectedNumber;
            } catch (InputMismatchException e) {
                System.out.println("1부터 " + maximumRange + "까지의 번호를 입력해주세요.");
                scanner.next();
            }
        }
    }

    // checkYesOrNo() : 주문(1), 주문 취소(2) 외 숫자 및 다른 자료형 예외 처리
    private int checkYesOrNo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int checkYesOrNo = scanner.nextInt();
                if (checkYesOrNo < 1 && checkYesOrNo > 2) { System.out.println("1 또는 2를 입력해주세요."); }
                else return checkYesOrNo;
            } catch (InputMismatchException e) {
                System.out.println("1 또는 2를 입력해주세요.");
                scanner.next();
            }
        }

    }

    // delay(int) : 이후 명령 실행까지 딜레이 걸기
    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds); // 딜레이 0.5 초
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
