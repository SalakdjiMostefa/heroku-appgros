package com.appgros.server.service.carts;

import com.appgros.common.dto.CartDTO;
import com.appgros.common.dto.ItemDTO;

import java.util.List;

public interface ICartService {
    CartDTO createCartToAnonym(List<ItemDTO> itemDTOS);

    CartDTO joinCartToUser(CartDTO cartDTO, String email);

 }
