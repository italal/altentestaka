package com.alten.ecomapp.back.service;

import com.alten.ecomapp.back.domain.FavoryProduct;
import com.alten.ecomapp.back.repository.FavoryProductRepository;
import com.alten.ecomapp.back.service.dto.FavoryProductDTO;
import com.alten.ecomapp.back.service.mapper.FavoryProductMapper;
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
 * Service Implementation for managing {@link FavoryProduct}.
 */
@Service
@Transactional
public class FavoryProductService {

    private static final Logger LOG = LoggerFactory.getLogger(FavoryProductService.class);

    private final FavoryProductRepository favoryProductRepository;

    private final FavoryProductMapper favoryProductMapper;

    public FavoryProductService(FavoryProductRepository favoryProductRepository, FavoryProductMapper favoryProductMapper) {
        this.favoryProductRepository = favoryProductRepository;
        this.favoryProductMapper = favoryProductMapper;
    }

    /**
     * Save a favoryProduct.
     *
     * @param favoryProductDTO the entity to save.
     * @return the persisted entity.
     */
    public FavoryProductDTO save(FavoryProductDTO favoryProductDTO) {
        LOG.debug("Request to save FavoryProduct : {}", favoryProductDTO);
        FavoryProduct favoryProduct = favoryProductMapper.toEntity(favoryProductDTO);
        favoryProduct = favoryProductRepository.save(favoryProduct);
        return favoryProductMapper.toDto(favoryProduct);
    }

    /**
     * Update a favoryProduct.
     *
     * @param favoryProductDTO the entity to save.
     * @return the persisted entity.
     */
    public FavoryProductDTO update(FavoryProductDTO favoryProductDTO) {
        LOG.debug("Request to update FavoryProduct : {}", favoryProductDTO);
        FavoryProduct favoryProduct = favoryProductMapper.toEntity(favoryProductDTO);
        favoryProduct = favoryProductRepository.save(favoryProduct);
        return favoryProductMapper.toDto(favoryProduct);
    }

    /**
     * Partially update a favoryProduct.
     *
     * @param favoryProductDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FavoryProductDTO> partialUpdate(FavoryProductDTO favoryProductDTO) {
        LOG.debug("Request to partially update FavoryProduct : {}", favoryProductDTO);

        return favoryProductRepository
            .findById(favoryProductDTO.getId())
            .map(existingFavoryProduct -> {
                favoryProductMapper.partialUpdate(existingFavoryProduct, favoryProductDTO);

                return existingFavoryProduct;
            })
            .map(favoryProductRepository::save)
            .map(favoryProductMapper::toDto);
    }

    /**
     * Get all the favoryProducts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
   /* @Transactional(readOnly = true)
    public Page<FavoryProductDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FavoryProducts");
        return favoryProductRepository.findAll(pageable).map(favoryProductMapper::toDto);
    }*/
    @Transactional(readOnly = true)
    public List<FavoryProductDTO> findAllByUserConnected() {
        LOG.debug("Request to get all FavoryProducts");
        return favoryProductRepository.findByUserIsCurrentUser().stream().map(favoryProductMapper::toDto).collect(Collectors.toList());
    }
    /**
     * Get one favoryProduct by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FavoryProductDTO> findOne(Long id) {
        LOG.debug("Request to get FavoryProduct : {}", id);
        return favoryProductRepository.findById(id).map(favoryProductMapper::toDto);
    }

    /**
     * Delete the favoryProduct by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FavoryProduct : {}", id);
        favoryProductRepository.deleteById(id);
    }
}
