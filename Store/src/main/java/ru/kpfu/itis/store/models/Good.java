 package ru.kpfu.itis.store.models;

 import org.hibernate.validator.constraints.*;

 import javax.persistence.*;
 import javax.validation.constraints.*;
 import java.io.Serializable;

 @Entity
 @Table(name = "good")
 public class Good implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true, nullable = false)
   private Long id;


   private String description;

   /*
   @NotNull
   @Pattern(regexp = "")
   @Column(unique = true)
*/
   @NotNull
   @Size(min = 1, max = 255)
   private String name;

   @NotNull
   @Range(min = 0L, max = 9999L)
   private Integer price;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "brand_id")
   private Brand brand;

   @NotNull
   @Column(unique = true)
   private String img;


   public Good(){

   }

   public Good(Long id, String name) {
     this.id = id;
     this.name = name;
   }

   public Long getId() {
     return id;
   }

   public void setId(Long id) {
     this.id = id;
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

   public Integer getPrice() {
     return price;
   }

   public void setPrice(Integer price) {
     this.price = price;
   }

   public Brand getBrand() {
     return brand;
   }

   public void setBrand(Brand brand) {
     this.brand = brand;
   }

   public String getImg() {
     return img;
   }

   public void setImg(String img) {
     this.img = img;
   }

 }
