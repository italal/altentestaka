package com.alten.ecomapp.back.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * A FavoryProduct.
 */
@Entity
@Table(name = "favory_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FavoryProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Long getId() {
        return this.id;
    }

    public FavoryProduct id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatedAt() {
        return this.createdAt;
    }

    public FavoryProduct createdAt(Integer createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return this.updatedAt;
    }

    public FavoryProduct updatedAt(Integer updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FavoryProduct user(User user) {
        this.setUser(user);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public FavoryProduct product(Product product) {
        this.setProduct(product);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FavoryProduct)) {
            return false;
        }
        return getId() != null && getId().equals(((FavoryProduct) o).getId());
    }

    @Override
    public int hashCode() {return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FavoryProduct{" +
            "id=" + getId() +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            "}";
    }
}
