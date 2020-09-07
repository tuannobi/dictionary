//package com.tuan.dictionary.user;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "user")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "isActive")
//    private boolean isActive;
//
//    @Column(name = "register_date")
//    private LocalDateTime registerDate;
//
//    @Column(name = "update_date")
//    private LocalDateTime updateDate;
//
//    @Column(name = "full_name")
//    private String fullName;
//
//    @Column(name = "phone_number")
//    private String phoneNumber;
//
//    @Column(name = "tokenTimestamp")
//    private Long tokenTimestamp;
//
//    @ManyToOne
//    @JoinColumn(name = "user_type",foreignKey = @ForeignKey(name = "FKUser848884"))
//
//}
