package com.tuan.dictionary.user;

import com.tuan.dictionary.purchasedetail.PurchaseDetail;
import com.tuan.dictionary.roleaccesscollection.RoleAccessCollection;
import com.tuan.dictionary.user.type.UserType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    @Column(name = "password")
    private String password;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "register_date")
    private LocalDateTime registerDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @NotBlank(message = "{user.name}")
    @Column(name = "full_name")
    private String fullName;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @NotBlank
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "token_timestamp")
    private Long tokenTimestamp;

    @ManyToOne
    @JoinColumn(name = "user_type",foreignKey = @ForeignKey(name = "FKUser848884"))
    private UserType userType;

//    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @OneToMany(mappedBy = "user")
    private List<PurchaseDetail> purchaseDetails;
    
    @OneToMany(mappedBy = "user")
    private List<RoleAccessCollection> roleAccessCollections;
    
    public List<RoleAccessCollection> getRoleAccessCollections() {
		return roleAccessCollections;
	}

	public void setRoleAccessCollections(List<RoleAccessCollection> roleAccessCollections) {
		this.roleAccessCollections = roleAccessCollections;
	}

	public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getTokenTimestamp() {
        return tokenTimestamp;
    }

    public void setTokenTimestamp(Long tokenTimestamp) {
        this.tokenTimestamp = tokenTimestamp;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                ", registerDate=" + registerDate +
                ", updateDate=" + updateDate +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", tokenTimestamp=" + tokenTimestamp +
                ", userType=" + userType +
                ", purchaseDetails=" + purchaseDetails +
                ", roleAccessCollections=" + roleAccessCollections +
                '}';
    }
}
