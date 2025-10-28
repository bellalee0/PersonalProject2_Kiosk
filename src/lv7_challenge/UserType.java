package lv7_challenge;

import java.math.BigDecimal;
import java.util.Arrays;

public enum UserType {
    VETERAN(1) {
        BigDecimal apply(BigDecimal totalPrice) {
            return totalPrice.multiply(new BigDecimal("0.9"));
        };
    },
    SOLDIER(2) {
        BigDecimal apply(BigDecimal totalPrice) {
            return totalPrice.multiply(new BigDecimal("0.95"));
        };
    },
    STUDENT(3) {
        BigDecimal apply(BigDecimal totalPrice) {
            return totalPrice.multiply(new BigDecimal("0.97"));
        };
    },
    GENERAL(4) {
        BigDecimal apply(BigDecimal totalPrice) {
            return totalPrice;
        };
    };

    int number;

    UserType(int number) {
        this.number = number;
    }

    public static UserType findUserType(int number) {
        return Arrays.stream(UserType.values())
                .filter(n -> n.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("범위 내 숫자를 지원해주세요."));
    }
}