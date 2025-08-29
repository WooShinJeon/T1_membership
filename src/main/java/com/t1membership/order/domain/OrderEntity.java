package com.t1membership.order.domain;

import com.t1membership.member.domain.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t1_order")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    @Column(name = "order_no",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderNo;

    @Column(name = "member_address",nullable = false)
    private String memberAddress;

    @ManyToOne
    @JoinColumn(name = "item_name",nullable = false)
    private ItemEntity itemName;

    @Column(name = "item_stock",nullable = false)
    private int itemStock;

    @Column(name = "item_price",nullable = false)
    private int itemPrice;

    @Column(name = "item_image",nullable = false)
    private String itemImage;

    @ManyToOne
    @JoinColumn(name = "member_email")
    private MemberEntity memberEntity;
}
