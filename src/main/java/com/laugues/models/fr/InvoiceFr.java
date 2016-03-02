package com.laugues.models.fr;

import com.laugues.models.DynamicFields;

import java.util.Map;

/**
 * Invoice mode for package FR
 */
public class InvoiceFr extends DynamicFields {

    private String id;
    private Double tvaFr;
    private Double amount;
    private Integer quantity;

    /**
     * Default constructor
     */
    public  InvoiceFr(){
        super();
    }

    /**
     * Constructor with empty #dynamicFields
     * @param id identifier
     * @param tvaFr TVA FR
     * @param amount amount
     * @param quantity quantity
     */
    public InvoiceFr(String id, Double tvaFr, Double amount, Integer quantity) {
        super();
        this.id = id;
        this.tvaFr = tvaFr;
        this.amount = amount;
        this.quantity = quantity;
    }

    /**
     * Constructor
     * @param id identifier
     * @param tvaFr TVA FR
     * @param amount amount
     * @param quantity quantity
     * @param dynamicFields all fields which are not known by InvoiceFr
     */
    public InvoiceFr(String id, Double tvaFr, Double amount, Integer quantity, Map<String, Object> dynamicFields) {
        super(dynamicFields);
        this.id = id;
        this.tvaFr = tvaFr;
        this.amount = amount;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTvaFr() {
        return tvaFr;
    }

    public void setTvaFr(Double tvaFr) {
        this.tvaFr = tvaFr;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "InvoiceFr{" +
                "id='" + id + '\'' +
                ", tvaFr=" + tvaFr +
                ", amount=" + amount +
                ", quantity=" + quantity +
                "} " + super.toString();
    }
}

