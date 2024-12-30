package com.alten.ecomapp.back.service.dto;

import com.alten.ecomapp.back.domain.enumeration.InventoryStatus;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.alten.ecomapp.back.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long id;

    private String code;

    @NotNull
    private String name;

    private String description;

    private String image;

    private String category;

    private Integer price;

    private Integer quantity;

    private String internalReference;

    private Integer shellId;

    private InventoryStatus inventoryStatus;

    private Integer rating;

    private Integer createdAt;

    private Integer updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInternalReference() {
        return internalReference;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Integer getShellId() {
        return shellId;
    }

    public void setShellId(Integer shellId) {
        this.shellId = shellId;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", category='" + getCategory() + "'" +
            ", price=" + getPrice() +
            ", quantity=" + getQuantity() +
            ", internalReference='" + getInternalReference() + "'" +
            ", shellId=" + getShellId() +
            ", inventoryStatus='" + getInventoryStatus() + "'" +
            ", rating=" + getRating() +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            "}";
    }
}
