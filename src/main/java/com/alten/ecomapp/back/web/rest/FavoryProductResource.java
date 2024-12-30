package com.alten.ecomapp.back.web.rest;

import com.alten.ecomapp.back.repository.FavoryProductRepository;
import com.alten.ecomapp.back.service.FavoryProductService;
import com.alten.ecomapp.back.service.dto.FavoryProductDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST controller for managing {@link com.alten.ecomapp.back.domain.FavoryProduct}.
 */
@RestController
@RequestMapping("/api/favory-products")
public class FavoryProductResource {

    private static final Logger LOG = LoggerFactory.getLogger(FavoryProductResource.class);

    private static final String ENTITY_NAME = "favoryProduct";

    @Value("${spring.clientApp.name}")
    private String applicationName;

    private final FavoryProductService favoryProductService;

    private final FavoryProductRepository favoryProductRepository;

    public FavoryProductResource(FavoryProductService favoryProductService, FavoryProductRepository favoryProductRepository) {
        this.favoryProductService = favoryProductService;
        this.favoryProductRepository = favoryProductRepository;
    }

    /**
     * {@code POST  /favory-products} : Create a new favoryProduct.
     *
     * @param favoryProductDTO the favoryProductDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new favoryProductDTO, or with status {@code 400 (Bad Request)} if the favoryProduct has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FavoryProductDTO> createFavoryProduct(@RequestBody FavoryProductDTO favoryProductDTO) throws URISyntaxException {
        LOG.debug("REST request to save FavoryProduct : {}", favoryProductDTO);
        if (favoryProductDTO.getId() != null) {
            return ResponseEntity.badRequest()
                    .body(null);
        }
        favoryProductDTO = favoryProductService.save(favoryProductDTO);
        return ResponseEntity.created(new URI("/favory-products/" + favoryProductDTO.getId()))
            .body(favoryProductDTO);
    }

    /**
     * {@code PUT  /favory-products/:id} : Updates an existing favoryProduct.
     *
     * @param id the id of the favoryProductDTO to save.
     * @param favoryProductDTO the favoryProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favoryProductDTO,
     * or with status {@code 400 (Bad Request)} if the favoryProductDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the favoryProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FavoryProductDTO> updateFavoryProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FavoryProductDTO favoryProductDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FavoryProduct : {}, {}", id, favoryProductDTO);
        if (favoryProductDTO.getId() == null || !Objects.equals(id, favoryProductDTO.getId()) || !favoryProductRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        favoryProductDTO = favoryProductService.update(favoryProductDTO);
        return ResponseEntity.ok()
            .body(favoryProductDTO);
    }

    /**
     * {@code PATCH  /favory-products/:id} : Partial updates given fields of an existing favoryProduct, field will ignore if it is null
     *
     * @param id the id of the favoryProductDTO to save.
     * @param favoryProductDTO the favoryProductDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated favoryProductDTO,
     * or with status {@code 400 (Bad Request)} if the favoryProductDTO is not valid,
     * or with status {@code 404 (Not Found)} if the favoryProductDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the favoryProductDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FavoryProductDTO> partialUpdateFavoryProduct(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FavoryProductDTO favoryProductDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FavoryProduct partially : {}, {}", id, favoryProductDTO);
        if (favoryProductDTO.getId() == null || !Objects.equals(id, favoryProductDTO.getId()) || !favoryProductRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        Optional<FavoryProductDTO> result = favoryProductService.partialUpdate(favoryProductDTO);

        return ResponseUtil.wrapOrNotFound(
            result
        );
    }

    /**
     * {@code GET  /favory-products} : get all the favoryProducts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of favoryProducts in body.
     */
   /* @GetMapping("")
    public ResponseEntity<List<FavoryProductDTO>> getAllFavoryProducts( Pageable pageable) {
        LOG.debug("REST request to get a page of FavoryProducts");
        Page<FavoryProductDTO> page = favoryProductService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }
    */

    @GetMapping("")
    public List<FavoryProductDTO> getAllFavoryProducts( Pageable pageable) {
        LOG.debug("REST request to get a page of FavoryProducts");
        List<FavoryProductDTO> page = favoryProductService.findAllByUserConnected();
        return page;
    }

    /**
     * {@code GET  /favory-products/:id} : get the "id" favoryProduct.
     *
     * @param id the id of the favoryProductDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the favoryProductDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FavoryProductDTO> getFavoryProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FavoryProduct : {}", id);
        Optional<FavoryProductDTO> favoryProductDTO = favoryProductService.findOne(id);
        return ResponseUtil.wrapOrNotFound(favoryProductDTO);
    }

    /**
     * {@code DELETE  /favory-products/:id} : delete the "id" favoryProduct.
     *
     * @param id the id of the favoryProductDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavoryProduct(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FavoryProduct : {}", id);
        favoryProductService.delete(id);
        return ResponseEntity.noContent()
            .build();
    }
}
