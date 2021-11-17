package com.appgros.server.service.carts;

import com.appgros.common.dto.CartDTO;
import com.appgros.common.dto.CartLineDTO;
import com.appgros.common.dto.ItemDTO;
import com.appgros.server.domain.cart.Cart;
import com.appgros.server.domain.cart.CartDAO;
import com.appgros.server.domain.cartline.CartLine;
import com.appgros.server.domain.cartline.CartLineDAO;
import com.appgros.server.domain.customer.Customer;
import com.appgros.server.domain.customer.CustomerDAO;
import com.appgros.server.domain.item.Item;
import com.appgros.server.domain.item.ItemDAO;
import com.appgros.server.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private CartDAO cartRepository;
    @Autowired
    private CartLineDAO cartLineDAO;
    @Autowired
    private ItemDAO itemDAO;
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public CartDTO createCartToAnonym(List<ItemDTO> itemDTOS) {
        List<CartLine> cartLines = new ArrayList<>();
        Cart cart = new Cart();

        Customer customer = customerDAO.findById(1L).get();
        cart.setCustomer(customer);

        cart = cartRepository.save(cart);
//        cart = cartRepository.save(cart);
        Cart finalCart = cart;
        itemDTOS.forEach(itemDTO -> {
            Item item = itemDAO.findById(itemDTO.getId()).get();
            CartLine cartLine = new CartLine();
//            cartLine = cartLineDAO.save(cartLine);
            cartLines.add(cartLine);
        });
        cart.setCartLines(cartLines);
        cart.setCartDate(new Date().toString());

        cart = cartRepository.save(cart);
        return transformCartToDTO(cart);
    }

    @Override
    public CartDTO joinCartToUser(CartDTO cartDTO, String email) {
        Customer customer = null;
        Cart cart = cartRepository.findById(cartDTO.getId()).get();
        cart.setCustomer(customer);
        List<CartLine> cartLines = new ArrayList<>();
        for (CartLineDTO cartLineDTO : cartDTO.getCartLineDTOS()) {
            CartLine cartLine = new CartLine();
            cartLine.setQuantity(cartLineDTO.getQuantity());
            cartLine.setUnitCost(cartLineDTO.getUnitCost());
            Item item = itemDAO.getOne(cartLineDTO.getItemId());
            cartLine.setItem(item);
            cartLine.setCart(cart);
            cartLine = cartLineDAO.save(cartLine);
            cartLines.add(cartLine);
        }
        cart.setCartLines(cartLines);
        cart.setCartDate(cartDTO.getCartDate());
        cart = cartRepository.saveAndFlush(cart);
        return transformCartToDTO(cart);
    }


    private CartDTO transformCartToDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCustomerId(cart.getCustomer().getId());
        cartDTO.setCartDate(cart.getCartDate());
        cartDTO.setId(cart.getId());
        List<CartLineDTO> cartLineDTOS = transformCartLinesToDTO(cart.getCartLines());
        cartDTO.setCartLineDTOS(cartLineDTOS);

        return cartDTO;
    }

    private List<CartLineDTO> transformCartLinesToDTO(Collection<CartLine> cartLines) {
        List<CartLineDTO> cartLineDTOS = new ArrayList<>();
        cartLines.forEach(cartLine -> {
            CartLineDTO cartLineDTO = transformCartLineToDTO(cartLine);
            cartLineDTOS.add(cartLineDTO);
        });
        return cartLineDTOS;
    }

    private CartLineDTO transformCartLineToDTO(CartLine cartLine) {
        CartLineDTO cartLineDTO = new CartLineDTO();

        cartLineDTO.setUnitCost(cartLine.getUnitCost());
        cartLineDTO.setItemId(cartLine.getItem().getId());
        cartLineDTO.setQuantity(cartLine.getQuantity());

        return cartLineDTO;
    }
}
