package lv7_challenge;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Kiosk {
    // start() : 스캐너로 주문 번호 받기 → 입력된 내용에 따라 결과 출력
    public void start(List<Menu> menuCategories) {
        List<Menu> categories = new ArrayList<>(menuCategories);
        // Cart 객체 생성
        Cart cart = new Cart();
        Display display = new Display();
        // execution : start() 메서드 진행 여부 (false가 될 시, start() 메서드 종료)
        boolean execution = true;

        while (execution) {
            // 카테고리 출력(출력된 항목 개수 반환) 및 번호 입력받기
            int categoryRange = display.printCategories(categories, cart);
            int selectedCategoryNumber = getNumber("카테고리", categoryRange);

            // 입력된 카테고리 번호 처리
            if (selectedCategoryNumber == 0) {
                // 0 입력 시 while문에서 빠져나오며 프로그램 종료
                System.out.println("프로그램을 종료합니다.");
                execution = false;
            } else if (selectedCategoryNumber >= 1 && selectedCategoryNumber <= categories.size()) {
                // 범위 내 숫자 입력 시 startCategory() 메서드 실행
                startCategory(cart, categories.get(selectedCategoryNumber - 1));
            } else if (selectedCategoryNumber == categories.size()+1) {
                // [Order Menu]의 Orders 선택 시
                startOrdering(cart, execution);
            } else if (selectedCategoryNumber == categories.size()+2) {
                // [Order Menu]의 Cancel 선택 시
                startCancel(cart);
            }
        }
    }


    // 키테고리 범위 내 선택 처리용 메서드
    // startCategory(Menu, Cart) : Menu의 MenuItem 출력하고, 번호 입력 받아서 처리
    private void startCategory(Cart cart, Menu menu) {
        boolean ordering = true;
        Display display = new Display();
        while (ordering) {
            // 해당 번호의 메뉴 출력
            display.printMenuItems(menu);
            // 메뉴 번호 입력받기 : getNumber(제목(메뉴), menuItem 리스트)
            int selectedOrderNumber = getNumber("메뉴", menu.getMenu().size());
            // 0(뒤로 가기) 입력 시 : while문 빠져나오며 카테고리 입력으로 되돌아감
            if (selectedOrderNumber == 0) {
                System.out.println("MAIN MENU로 돌아갑니다.");
                delay(300);
                ordering = false;
            } else if (selectedOrderNumber >= 1 && selectedOrderNumber <= menu.getMenu().size()) {
                // 범위 내 숫자 입력 시
                // 주문 내용 프린트
                System.out.println("주문 내용: " + menu.getMenu().get(selectedOrderNumber-1));
                delay(500);
                addToCart(cart, menu.getMenu().get(selectedOrderNumber-1));
                ordering = false;
            }
        }
    }
    // addToCart(Cart, MenuItem, boolean) : 장바구니에 추가 여부 묻고, 추가하면 추가한 뒤에, 아니면 바로 메인 메뉴로 돌아가기
    private void addToCart(Cart cart, MenuItem menuItem) {
        System.out.println("위 내용을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인      | 2. 취소");
        int checkAddingToCart = checkYesOrNo();
        if (checkAddingToCart == 1) {
            // 장바구니에 추가 O
            cart.saveItem(menuItem);
            cart.printCartItems();
            System.out.println("장바구니에 추가되었습니다.");
            System.out.println("MAIN MENU로 돌아갑니다.");
            delay(500);
        } else if (checkAddingToCart == 2) {
            // 장바구니에 추가 X
            System.out.println("주문을 취소합니다.");
            System.out.println("MAIN MENU로 돌아갑니다.");
            delay(500);
        }
    }
    // getNumber(String, int) : 숫자를 입력하고, 범위 외 숫자 혹은 다른 자료형 예외 처리
    private int getNumber(String name, int maximumRange) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(name + " 번호를 입력하세요: ");
            try {
                int selectedNumber = scanner.nextInt();
                if (selectedNumber < 0 || selectedNumber > maximumRange) { System.out.println("0부터 " + maximumRange + "까지의 번호를 입력해주세요."); }
                else return selectedNumber;
            } catch (InputMismatchException e) {
                System.out.println("0부터 " + maximumRange + "까지의 번호를 입력해주세요.");
                scanner.next();
            }
        }
    }


    // Orders 선택 처리용 메서드
    // startOrdering(Cart, boolean) : 장바구니 리스트 출력 후 주문 여부 확인받아서 처리
    private void startOrdering(Cart cart, boolean execution) {
        Display display = new Display();
        // 장바구니 리스트 출력 (메뉴 개수, 총 금액)
        display.printCartList(cart);
        System.out.println("주문하시겠습니까?");
        System.out.println("1. 확인      | 2. 취소 (장바구니 초기화)");
        int checkAddingToCart = checkYesOrNo();
        if (checkAddingToCart == 1) {
            // 주문 O : 할인 정보 입력 받고, 최종 금액 출력 후 프로그램 종료
            BigDecimal finalPrice = getDiscount(cart.getTotalPrice());
            System.out.println("주문이 완료되었습니다. 총 " + cart.getCartItems().size() + "개 항목, 할인 적용 후 최종 금액은 ₩ " + finalPrice + "입니다.");
            delay(500);
            System.out.println("프로그램이 종료됩니다.");
            execution = false;
        } else if (checkAddingToCart == 2) {
            // 주문 X : 장바구니 초기화
            cart.clearCart();
            System.out.println("장바구니가 초기화되었습니다. 주문을 다시 시작합니다.");
            delay(500);
        }
    }
    // UserType 출력 후 입력받고, UserType 반환받기 → 할인율 적용하여 최종 금액 계산 후 반환
    private BigDecimal getDiscount(BigDecimal totalPrice) {
        Display display = new Display();
        display.printUserType();
        Scanner scanner = new Scanner(System.in);
        int selectedNumber = 4;
        boolean getNumber = false;
        while (!getNumber) {
            try {
                selectedNumber = scanner.nextInt();
                if (selectedNumber >= 1 || selectedNumber <= 4) {
                    getNumber = true;
                } else {
                    System.out.println("1부터 4까지의 번호를 입력해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("1부터 4까지의 번호를 입력해주세요.");
                scanner.next();
            }
        }
        UserType userType = UserType.findUserType(selectedNumber);
        return userType.apply(totalPrice);
    }


    // startCancel(Cart) : 전체 삭제 or 특정 주문만 삭제
    private void startCancel(Cart cart) {
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
            String removeItem = getCancelItem(cart);
            if (isInteger(removeItem)) {
                cart.removeItem(Integer.parseInt(removeItem)-1);
            } else {
                cart.removeItem(removeItem);
            }
            delay(300);
            cart.printCartItems();
            System.out.println("선택한 메뉴가 삭제되었습니다. 주문을 다시 시작합니다.");
            delay(500);
        }
    }
    // getCancelItem(Cart) : 메뉴의 번호 또는 메뉴명 입력받기 (번호 범위 혹은 메뉴명 불일치 시 InputMismatchException로 다시 실행)
    private String getCancelItem(Cart cart) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("삭제하고 싶은 메뉴의 번호 또는 메뉴명을 입력하세요: ");
            try {
                String cancelItem = scanner.nextLine();
                if (isInteger(cancelItem)) {
                    if (Integer.parseInt(cancelItem) < 0 || Integer.parseInt(cancelItem) > cart.getCartItems().size()) { System.out.println("잘못된 입력입니다."); }
                    else return cancelItem;
                } else if (cart.getCartItems().stream().map(menuItem -> menuItem.getName()).collect(Collectors.toList()).contains(cancelItem)) {
                    return cancelItem;
                } else { System.out.println("잘못된 입력입니다."); }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                scanner.next();
            }
        }
    }
    // isInteger(String) : 입력된 문자열이 숫자인지 확인
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // checkYesOrNo() : 주문(1), 주문 취소(2) 외 숫자 및 다른 자료형 예외 처리
    // addToCart(), startOrdering(), startCancel에서 사용
    private int checkYesOrNo() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int checkYesOrNo = scanner.nextInt();
                if (checkYesOrNo < 1 || checkYesOrNo > 2) { System.out.println("1 또는 2를 입력해주세요."); }
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
