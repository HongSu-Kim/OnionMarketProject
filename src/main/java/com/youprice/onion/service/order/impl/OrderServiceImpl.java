package com.youprice.onion.service.order.impl;

import com.youprice.onion.dto.order.OrderAddDTO;
import com.youprice.onion.entity.member.Member;
import com.youprice.onion.entity.order.Delivery;
import com.youprice.onion.entity.order.Order;
import com.youprice.onion.entity.product.Product;
import com.youprice.onion.repository.member.MemberRepository;
import com.youprice.onion.repository.order.DeliveryRepository;
import com.youprice.onion.repository.order.OrderRepository;
import com.youprice.onion.repository.product.ProductRepository;
import com.youprice.onion.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
//    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    // 주문 완료
    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {

//        Member member = memberRepository.findById(orderAddDTO.getMemberId()).orElse(null);
//        Product product = productRepository.findById(orderAddDTO.getProductId()).orElse(null);
        Member member = null;//--
        Product product = null;//--

        // 주문내역 생성
        Order order = new Order(member, product, orderAddDTO.getOrderPrice());
        orderRepository.save(order);

        // 배송정보 생성
        Delivery delivery = new Delivery(order, orderAddDTO.getPostcode(), orderAddDTO.getAddress(), orderAddDTO.getDetailAddress(),
                orderAddDTO.getExtraAddress(), orderAddDTO.getRequest(), orderAddDTO.getDeliveryCost());
        deliveryRepository.save(delivery);
    }
}
