package main.java.Hibernate.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products_sold")
public class ProductsSold {

    @Id
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "product_amount")
    private int product_amount;

}
