package com.alten.ecomapp.back.service.mapper;

import com.alten.ecomapp.back.domain.Product;
import com.alten.ecomapp.back.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {}
