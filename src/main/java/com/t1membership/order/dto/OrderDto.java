package com.t1membership.order.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderNo; //주문번호
    private String memberEmail; //회원 아이디
    private String memberName; //회원 이름
    private String itemName;//상품 이름
    private int itemPrice; //상품 가격
    private int itemStock; //상품 수량
    private String itemImage; //상품 이미지
    private int orderTotalPrice;//주문 총 가격
}
