package com.alten.ecomapp.back.web.rest;

import com.alten.ecomapp.back.repository.ProductRepository;
import com.alten.ecomapp.back.service.ProductService;
import com.alten.ecomapp.back.service.dto.ProductDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.alten.ecomapp.back.domain.Product}.
 */
@RestController
@RequestMapping("/api/products")
public class ProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    @Value("${spring.clientApp.name}")
    private String applicationName;

    private final ProductService productService;

    private final ProductRepository productRepository;

    public ProductResource(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    /**
     * {@code POST  /products} : Create a new product.
     *
     * @param productDTO the productDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productDTO, or with status {@code 400 (Bad Request)} if the product has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        LOG.debug("REST request to save Product : {}", productDTO);
        if (productDTO.getId() != null) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
        productDTO = productService.save(productDTO);
        return ResponseEntity.created(new URI("/products/" + productDTO.getId()))
                .body(productDTO);
    }

    /**
     * {@code PUT  /products/:id} : Updates an existing product.
     *
     * @param id         the id of the productDTO to save.
     * @param productDTO the productDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productDTO,
     * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody ProductDTO productDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Product : {}, {}", id, productDTO);
        if (productDTO.getId() == null || !Objects.equals(id, productDTO.getId()) || !productRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        productDTO = productService.update(productDTO);
        return ResponseEntity.ok()
                .body(productDTO);
    }


    /**
     * {@code GET  /products} : get all the products.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of products in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProducts(Pageable pageable) {
        LOG.debug("REST request to get a page of Products");
        Page<ProductDTO> page = productService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /products/:id} : get the "id" product.
     *
     * @param id the id of the productDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Product : {}", id);
        Optional<ProductDTO> productDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDTO);
    }

    /**
     * {@code DELETE  /products/:id} : delete the "id" product.
     *
     * @param id the id of the productDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Product : {}", id);
            productService.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
