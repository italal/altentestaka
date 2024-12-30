package com.alten.ecomapp.back.service;

import com.alten.ecomapp.back.domain.CartProduct;
import com.alten.ecomapp.back.repository.CartProductRepository;
import com.alten.ecomapp.back.service.dto.CartProductDTO;
import com.alten.ecomapp.back.service.dto.CompleteUserDTO;
import com.alten.ecomapp.back.service.dto.UserDTO;
import com.alten.ecomapp.back.service.mapper.CartProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link CartProduct}.
 */
@Service
@Transactional
public class CartProductService {

    private static final Logger LOG = LoggerFactory.getLogger(CartProductService.class);

    private final CartProductRepository cartProductRepository;

    private final CartProductMapper cartProductMapper;
    private final UserService userService;

    public CartProductService(CartProductRepository cartProductRepository, CartProductMapper cartProductMapper, UserService userService) {
        this.cartProductRepository = cartProductRepository;
        this.cartProductMapper = cartProductMapper;
        this.userService = userService;
    }

    /**
     * Save a cartProduct.
     *
     * @param cartProductDTO the entity to save.
     * @return the persisted entity.
     */
    public CartProductDTO save(CartProductDTO cartProductDTO) {
        LOG.debug("Request to save CartProduct : {}", cartProductDTO);
        // retreive current connected user to set in cartProduct
        Optional<CompleteUserDTO> currentUser = userService.getCurrentUser();

        UserDTO userDTO = new UserDTO();
        userDTO.setId(currentUser.get().getId());
        cartProductDTO.setUser(userDTO);


        CartProduct cartProduct = cartProductMapper.toEntity(cartProductDTO);
        cartProduct = cartProductRepository.save(cartProduct);
        return cartProductMapper.toDto(cartProduct);
    }

    /**
     * Update a cartProduct.
     *
     * @param cartProductDTO the entity to save.
     * @return the persisted entity.
     */
    public CartProductDTO update(CartProductDTO cartProductDTO) {
        LOG.debug("Request to update CartProduct : {}", cartProductDTO);
        CartProduct cartProduct = cartProductMapper.toEntity(cartProductDTO);
        cartProduct = cartProductRepository.save(cartProduct);
        return cartProductMapper.toDto(cartProduct);
    }

    /**
     * Partially update a cartProduct.
     *
     * @param cartProductDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CartProductDTO> partialUpdate(CartProductDTO cartProductDTO) {
        LOG.debug("Request to partially update CartProduct : {}", cartProductDTO);

        return cartProductRepository
                .findById(cartProductDTO.getId())
                .map(existingCartProduct -> {
                    cartProductMapper.partialUpdate(existingCartProduct, cartProductDTO);

                    return existingCartProduct;
                })
                .map(cartProductRepository::save)
                .map(cartProductMapper::toDto);
    }

    /**
     * Get all the cartProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<CartProductDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all CartProducts");
       // Optional<CompleteUserDTO> userDTO=userService.getCurrentUser();
        return cartProductRepository.findAll(pageable).map(cartProductMapper::toDto);
    }
    @Transactional(readOnly = true)
    public  List<CartProductDTO>  findAllByUser() {

        return  cartProductRepository.findByUserIsCurrentUser().stream() .map(cartProductMapper::toDto).collect(Collectors.toList()); // Calls the static mapper method

    }


    /**
     * Get one cartProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CartProductDTO> findOne(Long id) {
        LOG.debug("Request to get CartProduct : {}", id);
        return cartProductRepository.findById(id).map(cartProductMapper::toDto);
    }

    /**
     * Delete the cartProduct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete CartProduct : {}", id);
        cartProductRepository.deleteById(id);
    }
}
