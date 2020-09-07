package com.tuan.dictionary.purchasedetail;

import com.tuan.dictionary.collection.Collection;
import com.tuan.dictionary.purchasedetail.purchasestatus.PurchaseStatus;
import com.tuan.dictionary.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail implements Serializable {
    @Id
    @ManyToOne
    private User user;

    @Id
    @ManyToOne
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "purchase_status",foreignKey = @ForeignKey(name = "FKPurchase_D456446"))
    private PurchaseStatus purchaseStatus;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    @Column(name = "inprogress_date")
    private LocalDateTime inprogessDate;

    @Column(name = "cancel_date")
    private LocalDateTime cancelDate;

    @Column(name = "success_date")
    private LocalDateTime successDate;

    @Column(name = "purchasing_person")
    private Long purchasingPerson;

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getInprogessDate() {
        return inprogessDate;
    }

    public void setInprogessDate(LocalDateTime inprogessDate) {
        this.inprogessDate = inprogessDate;
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }

    public LocalDateTime getSuccessDate() {
        return successDate;
    }

    public void setSuccessDate(LocalDateTime successDate) {
        this.successDate = successDate;
    }

    public Long getPurchasingPerson() {
        return purchasingPerson;
    }

    public void setPurchasingPerson(Long purchasingPerson) {
        this.purchasingPerson = purchasingPerson;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        PurchaseDetail that = (PurchaseDetail) o;
        return Objects.equals( user, that.user ) &&
                Objects.equals( collection, that.collection );
    }

    @Override
    public int hashCode() {
        return Objects.hash( user, collection );
    }
}
