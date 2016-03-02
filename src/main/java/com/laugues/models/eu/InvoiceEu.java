package com.laugues.models.eu;

import com.laugues.models.DynamicFields;

import java.util.Map;

/**
 * Invoice model for package Eu
 */
public class InvoiceEu extends DynamicFields {

    private String id;
    private String tvaIntraCom;
    private Double amount;

    /**
     * Default constructor
     */
    public InvoiceEu() {
        super();
    }

    /**
     * Constructor
     * @param id identifier
     * @param tvaIntraCom TVA intra com
     * @param amount amount
     * @param dynamicFields all fields which are not known by InvoiceEu
     */
    public InvoiceEu(String id, String tvaIntraCom, Double amount,Map<String, Object> dynamicFields) {
        super(dynamicFields);
        this.id = id;
        this.tvaIntraCom = tvaIntraCom;
        this.amount = amount;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTvaIntraCom() {
        return tvaIntraCom;
    }

    public void setTvaIntraCom(String tvaIntraCom) {
        this.tvaIntraCom = tvaIntraCom;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
