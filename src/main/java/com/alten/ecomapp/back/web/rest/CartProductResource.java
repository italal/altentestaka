package com.alten.ecomapp.back.web.rest;

import com.alten.ecomapp.back.repository.CartProductRepository;
import com.alten.ecomapp.back.service.CartProductService;
import com.alten.ecomapp.back.service.dto.CartProductDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link com.alten.ecomapp.back.domain.CartProduct}.
 */
@RestController
@RequestMapping("/api/cart-products")
public class CartProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(CartProductResource.class);

    private static final String ENTITY_NAME = "cartProduct";

    @Value("${spring.clientApp.name}")
    private String applicationName;

    private final CartProductService cartProductService;

    private final CartProductRepository cartProductRepository;

    public CartProductResource(CartProductService cartProductService, CartProductRepository cartProductRepository) {
        this.cartProductService = cartProductService;
        this.cartProductRepository = cartProductRepository;
    }

    /**
     * {@code POST  /cart-products} : Create a new cartProduct.
     *
     * @param cartProductDTO the cartProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cartProductDTO, or with status {@code 400 (Bad Request)} if the cartProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CartProductDTO> createCartProduct(@RequestBody CartProductDTO cartProductDTO) throws URISyntaxException {
        LOG.debug("REST request to save CartProduct : {}", cartProductDTO);
        if (cartProductDTO.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }

        cartProductDTO = cartProductService.save(cartProductDTO);
        return ResponseEntity.created(new URI("/cart-products/" + cartProductDTO.getId()))
            .body(cartProductDTO);
    }

    /**
     * {@code PUT  /cart-products/:id} : Updates an existing cartProduct.
     *
     * @param id the id of the cartProductDTO to save.
     * @param cartProductDTO the cartProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartProductDTO,
     * or with status {@code 400 (Bad Request)} if the cartProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cartProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CartProductDTO> updateCartProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CartProductDTO cartProductDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update CartProduct : {}, {}", id, cartProductDTO);
        if (cartProductDTO.getId() == null || !Objects.equals(id, cartProductDTO.getId()) || !cartProductRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        cartProductDTO = cartProductService.update(cartProductDTO);
        return ResponseEntity.ok()
            .body(cartProductDTO);
    }

    /**
     * {@code PATCH  /cart-products/:id} : Partial updates given fields of an existing cartProduct, field will ignore if it is null
     *
     * @param id the id of the cartProductDTO to save.
     * @param cartProductDTO the cartProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cartProductDTO,
     * or with status {@code 400 (Bad Request)} if the cartProductDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cartProductDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cartProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CartProductDTO> partialUpdateCartProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CartProductDTO cartProductDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update CartProduct partially : {}, {}", id, cartProductDTO);
        if (cartProductDTO.getId() == null || !Objects.equals(id, cartProductDTO.getId()) || !cartProductRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        Optional<CartProductDTO> result = cartProductService.partialUpdate(cartProductDTO);

        return ResponseUtil.wrapOrNotFound(
            result
        );
    }

    /**
     * {@code GET  /cart-products} : get all the cartProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cartProducts in body.
     */
    /*@GetMapping("")
    public ResponseEntity<List<CartProductDTO>> getAllCartProducts(Pageable pageable) {
        LOG.debug("REST request to get a page of CartProducts");
       // Page<CartProductDTO> page = cartProductService.findAllByUser();
        Page<CartProductDTO> page = cartProductService.findAllByUser();
        return ResponseEntity.ok().body(page.getContent());
    }*/
    @GetMapping("")
    public List<CartProductDTO> getAllCartProducts(Pageable pageable) {
        LOG.debug("REST request to get a page of CartProducts");
        // Page<CartProductDTO> page = cartProductService.findAllByUser();
        List<CartProductDTO> page = cartProductService.findAllByUser();
        return page;
    }

    /**
     * {@code GET  /cart-products/:id} : get the "id" cartProduct.
     *
     * @param id the id of the cartProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cartProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CartProductDTO> getCartProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to get CartProduct : {}", id);
        Optional<CartProductDTO> cartProductDTO = cartProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cartProductDTO);
    }

    /**
     * {@code DELETE  /cart-products/:id} : delete the "id" cartProduct.
     *
     * @param id the id of the cartProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete CartProduct : {}", id);
        cartProductService.delete(id);
        return ResponseEntity.noContent()
            .build();
    }
}
