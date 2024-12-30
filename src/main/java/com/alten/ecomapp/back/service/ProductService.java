package com.alten.ecomapp.back.service;

import com.alten.ecomapp.back.domain.Product;
import com.alten.ecomapp.back.repository.ProductRepository;
import com.alten.ecomapp.back.repository.UserRepository;
import com.alten.ecomapp.back.service.dto.CompleteUserDTO;
import com.alten.ecomapp.back.service.dto.ProductDTO;
import com.alten.ecomapp.back.service.dto.UserDTO;
import com.alten.ecomapp.back.service.mapper.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    @Autowired
    private UserService userService;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Save a product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDTO save(ProductDTO productDTO) {
        Optional<CompleteUserDTO> userDTO=userService.getCurrentUser();
        if(userDTO.get().getEmail().equals("admin@admin.com")) {
            LOG.debug("Request to save Product : {}", productDTO);
            Product product = productMapper.toEntity(productDTO);
            product = productRepository.save(product);
            return productMapper.toDto(product);
        }else {
            throw new RuntimeException("username not autorelease for this opperation");
        }

    }

    /**
     * Update a product.
     *
     * @param productDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductDTO update(ProductDTO productDTO) {
        Optional<CompleteUserDTO> userDTO=userService.getCurrentUser();
        if(userDTO.get().getEmail().equals("admin@admin.com")) {
        LOG.debug("Request to update Product : {}", productDTO);
        Product product = productMapper.toEntity(productDTO);
        product = productRepository.save(product);
        return productMapper.toDto(product);}
        else{
            throw new RuntimeException("username not autorelease for this opperation");
        }
    }

    /**
     * Partially update a product.
     *
     * @param productDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
        LOG.debug("Request to partially update Product : {}", productDTO);

        return productRepository
            .findById(productDTO.getId())
            .map(existingProduct -> {
                productMapper.partialUpdate(existingProduct, productDTO);

                return existingProduct;
            })
            .map(productRepository::save)
            .map(productMapper::toDto);
    }

    /**
     * Get all the products.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all Products");
        return productRepository.findAll(pageable).map(productMapper::toDto);
    }

    /**
     * Get one product by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        LOG.debug("Request to get Product : {}", id);
        return productRepository.findById(id).map(productMapper::toDto);
    }

    /**
     * Delete the product by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        Optional<CompleteUserDTO> userDTO=userService.getCurrentUser();
        if(userDTO.get().getEmail().equals("admin@admin.com")) {
            LOG.debug("Request to delete Product : {}", id);
            productRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("username not autorelease for this opperation");
        }
    }
}
