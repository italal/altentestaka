package com.alten.ecomapp.back.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 * A CartProduct.
 */
@Entity
@Table(name = "cart_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "count")
    private Integer count;

    @Column(name = "created_at")
    private Integer createdAt;

    @Column(name = "updated_at")
    private Integer updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public Long getId() {
        return this.id;
    }

    public CartProduct id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount() {
        return this.count;
    }

    public CartProduct count(Integer count) {
        this.setCount(count);
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCreatedAt() {
        return this.createdAt;
    }

    public CartProduct createdAt(Integer createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedAt() {
        return this.updatedAt;
    }

    public CartProduct updatedAt(Integer updatedAt) {
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

    public CartProduct user(User user) {
        this.setUser(user);
        return this;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartProduct product(Product product) {
        this.setProduct(product);
        return this;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CartProduct)) {
            return false;
        }
        return getId() != null && getId().equals(((CartProduct) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CartProduct{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", createdAt=" + getCreatedAt() +
            ", updatedAt=" + getUpdatedAt() +
            "}";
    }
}
