package com.alten.ecomapp.back.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.alten.ecomapp.back.domain.FavoryProduct} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FavoryProductDTO implements Serializable {

    private Long id;

    private Integer createdAt;

    private Integer updatedAt;

    private UserDTO user;

    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FavoryProductDTO)) {
            return false;
        }

        FavoryProductDTO favoryProductDTO = (FavoryProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, favoryProductDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FavoryProductDTO{" +
            "id=" + getId() +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            ", user=" + getUser() +
            ", product=" + getProduct() +
            "}";
    }
}
