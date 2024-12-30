package com.alten.ecomapp.back.service.mapper;

import com.alten.ecomapp.back.domain.FavoryProduct;
import com.alten.ecomapp.back.domain.Product;
import com.alten.ecomapp.back.domain.User;
import com.alten.ecomapp.back.service.dto.FavoryProductDTO;
import com.alten.ecomapp.back.service.dto.ProductDTO;
import com.alten.ecomapp.back.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FavoryProduct} and its DTO {@link FavoryProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface FavoryProductMapper extends EntityMapper<FavoryProductDTO, FavoryProduct> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    FavoryProductDTO toDto(FavoryProduct s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);
}
