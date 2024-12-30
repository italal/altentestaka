package com.alten.ecomapp.back.service.mapper;

import com.alten.ecomapp.back.domain.CartProduct;
import com.alten.ecomapp.back.domain.Product;
import com.alten.ecomapp.back.domain.User;
import com.alten.ecomapp.back.service.dto.CartProductDTO;
import com.alten.ecomapp.back.service.dto.ProductDTO;
import com.alten.ecomapp.back.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CartProduct} and its DTO {@link CartProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartProductMapper extends EntityMapper<CartProductDTO, CartProduct> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    CartProductDTO toDto(CartProduct s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);
}
