package com.alten.ecomapp.back.domain;

import com.alten.ecomapp.back.domain.enumeration.InventoryStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "internal_reference")
    private String internalReference;

    @Column(name = "shell_id")
    private Integer shellId;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus inventoryStatus;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Product code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public Product image(String image) {
        this.setImage(image);
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return this.category;
    }

    public Product category(String category) {
        this.setCategory(category);
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Product price(Integer price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Product quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInternalReference() {
        return this.internalReference;
    }

    public Product internalReference(String internalReference) {
        this.setInternalReference(internalReference);
        return this;
    }

    public void setInternalReference(String internalReference) {
        this.internalReference = internalReference;
    }

    public Integer getShellId() {
        return this.shellId;
    }

    public Product shellId(Integer shellId) {
        this.setShellId(shellId);
        return this;
    }

    public void setShellId(Integer shellId) {
        this.shellId = shellId;
    }

    public InventoryStatus getInventoryStatus() {
        return this.inventoryStatus;
    }

    public Product inventoryStatus(InventoryStatus inventoryStatus) {
        this.setInventoryStatus(inventoryStatus);
        return this;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Integer getRating() {
        return this.rating;
    }

    public Product rating(Integer rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getCreatedAt() {
        return this.createdAt;
    }

    public Product createdAt(Integer createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return this.updatedAt;
    }

    public Product updatedAt(Integer updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return getId() != null && getId().equals(((Product) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
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
