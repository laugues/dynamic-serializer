package com.laugues.utils;

import com.laugues.models.eu.InvoiceEu;
import com.laugues.models.fr.InvoiceFr;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lds on 01/03/2016.
 */
public class TestUtils {

    public static final Map<String, Object> INVOICE_FR_DYNAMIC_FIELDS = new LinkedHashMap<String, Object>();
    public static final Map<String, Object> INVOICE_EU_DYNAMIC_FIELDS = new LinkedHashMap<String, Object>();

    public static final String INVOICE_FR_JSON_STRING = "{\r\n" +
            "  \"dynamicFields\" : {\r\n" +
            "    \"supplierId\" : \"15254\",\r\n" +
            "    \"clientId\" : \"10001\",\r\n" +
            "    \"frRules\" : [ \"Rule 1\", \"Rule 2\", \"Rule 3\" ]\r\n" +
            "  },\r\n" +
            "  \"id\" : \"12415_1524545\",\r\n" +
            "  \"tvaFr\" : 0.2,\r\n" +
            "  \"amount\" : 12548.58,\r\n" +
            "  \"quantity\" : 8\r\n" +
            "}";

    public static final String INVOICE_EU_JSON_STRING = "{\r\n" +
            "  \"dynamicFields\" : {\r\n" +
            "    \"euConvention\" : \"Europe Convention Description\",\r\n" +
            "    \"euRules\" : [ \"First Europe Rule description\", \"Second Europe Rule description\", \"Third Europe Rule description\" ]\r\n" +
            "  },\r\n" +
            "  \"id\" : \"12415_9999999\",\r\n" +
            "  \"tvaIntraCom\" : \"434189163\",\r\n" +
            "  \"amount\" : 12948.99\r\n" +
            "}";


    public static final InvoiceFr INVOICE_FR = new InvoiceFr("12415_1524545", new Double(0.2), new Double(12548.58), 8, INVOICE_FR_DYNAMIC_FIELDS);
    public static final InvoiceEu INVOICE_EU = new InvoiceEu("12415_9999999", "434189163", new Double(12948.99), INVOICE_EU_DYNAMIC_FIELDS);

    static {
        INVOICE_FR_DYNAMIC_FIELDS.put("supplierId", "15254");
        INVOICE_FR_DYNAMIC_FIELDS.put("clientId", "10001");


        List<String> frRules = new ArrayList<String>();
        frRules.add("Rule 1");
        frRules.add("Rule 2");
        frRules.add("Rule 3");
        INVOICE_FR_DYNAMIC_FIELDS.put("frRules", frRules);



        INVOICE_EU_DYNAMIC_FIELDS.put("euConvention", "Europe Convention Description");

        List<String> euRules = new ArrayList<String>();
        euRules.add("First Europe Rule description");
        euRules.add("Second Europe Rule description");
        euRules.add("Third Europe Rule description");
        INVOICE_EU_DYNAMIC_FIELDS.put("euRules", euRules);
    }


    protected void assertDynamicFieldsFr(InvoiceFr expectedInvoiceFr, InvoiceFr actualInvoiceFR) {
        expectedInvoiceFr.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoiceFR.getDynamicFields().get(key));
        });
    }


    protected void assertDynamicFieldsEu(InvoiceEu expectedInvoiceEu, InvoiceEu actualInvoiceEu) {
        expectedInvoiceEu.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoiceEu.getDynamicFields().get(key));
        });
    }
}
