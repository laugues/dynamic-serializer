package com.laugues.serializer;

import com.laugues.models.eu.InvoiceEu;
import com.laugues.models.fr.InvoiceFr;
import com.laugues.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DynamicSerializerTest extends TestUtils {


    @Test
    public void testDeSerializeFrToFr() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class);
        InvoiceFr actualInvoiceFR = (InvoiceFr) serializer.deSerialize(INVOICE_FR_JSON_STRING);
        Assert.assertEquals(INVOICE_FR.getId(), actualInvoiceFR.getId());
        Assert.assertEquals(INVOICE_FR.getAmount(), actualInvoiceFR.getAmount());
        Assert.assertEquals(INVOICE_FR.getTvaFr(), actualInvoiceFR.getTvaFr());
        assertDynamicFieldsFr(INVOICE_FR, actualInvoiceFR);
    }


    @Test
    public void testDeSerializeEuToEu() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceEu.class);
        InvoiceEu actualInvoiceEu = (InvoiceEu) serializer.deSerialize(INVOICE_EU_JSON_STRING);
        Assert.assertEquals(INVOICE_EU.getId(), actualInvoiceEu.getId());
        Assert.assertEquals(INVOICE_EU.getAmount(), actualInvoiceEu.getAmount());
        Assert.assertEquals(INVOICE_EU.getTvaIntraCom(), actualInvoiceEu.getTvaIntraCom());
        assertDynamicFieldsEu(INVOICE_EU, actualInvoiceEu);
    }

    @Test
    public void testDeSerializeEuToFr() throws IOException {
        DynamicSerializer deSerializer = new DynamicSerializer(InvoiceFr.class);
        InvoiceFr actualInvoiceFr = (InvoiceFr) deSerializer.deSerialize(INVOICE_EU_JSON_STRING);
        Assert.assertEquals(INVOICE_EU.getId(), actualInvoiceFr.getId());
        Assert.assertEquals(INVOICE_EU.getAmount(), actualInvoiceFr.getAmount());
        Assert.assertEquals(INVOICE_EU.getTvaIntraCom(), actualInvoiceFr.getDynamicFields().get("tvaIntraCom"));
        INVOICE_EU.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoiceFr.getDynamicFields().get(key));
        });
    }


    @Test
    public void testDeSerializeFrToEu() throws IOException {
        DynamicSerializer deSerializer = new DynamicSerializer(InvoiceEu.class);
        InvoiceEu actualInvoiceEu = (InvoiceEu) deSerializer.deSerialize(INVOICE_FR_JSON_STRING);
        Assert.assertEquals(INVOICE_FR.getId(), actualInvoiceEu.getId());
        Assert.assertEquals(INVOICE_FR.getAmount(), actualInvoiceEu.getAmount());
        Assert.assertEquals(INVOICE_FR.getTvaFr(), actualInvoiceEu.getDynamicFields().get("tvaFr"));
        Assert.assertEquals(INVOICE_FR.getQuantity(), actualInvoiceEu.getDynamicFields().get("quantity"));
        INVOICE_FR.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoiceEu.getDynamicFields().get(key));
        });
    }


    @Test
    public void testSerializeFrToFr() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class);
        String actual = serializer.serialize(INVOICE_FR);
        Assert.assertEquals(INVOICE_FR_JSON_STRING, actual);
    }

    @Test
    public void testSerializeFrToEu() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceEu.class);
        String actual = serializer.serialize(INVOICE_FR);
        Assert.assertEquals(INVOICE_FR_JSON_STRING,actual);
    }

    @Test
    public void testSerializeEuToEu() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceEu.class);
        String actual = serializer.serialize(INVOICE_EU);
        Assert.assertEquals(INVOICE_EU_JSON_STRING,actual);
    }

    @Test
    public void testTransformEuToFr() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceEu.class, InvoiceFr.class);
        InvoiceFr actualInvoice = (InvoiceFr) serializer.transform(INVOICE_EU);

        Assert.assertEquals(INVOICE_EU.getId(), actualInvoice.getId());
        Assert.assertEquals(INVOICE_EU.getAmount(), actualInvoice.getAmount());
        Assert.assertNull(actualInvoice.getTvaFr());
        Assert.assertNull(actualInvoice.getQuantity());
        Assert.assertEquals(INVOICE_EU.getTvaIntraCom(), actualInvoice.getDynamicFields().get("tvaIntraCom"));

        INVOICE_EU.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoice.getDynamicFields().get(key));
        });
    }

    @Test
    public void testTransformFrToEu() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class, InvoiceEu.class);
        InvoiceEu actualInvoice = (InvoiceEu) serializer.transform(INVOICE_FR);


        Assert.assertEquals(INVOICE_FR.getId(), actualInvoice.getId());
        Assert.assertEquals(INVOICE_FR.getAmount(), actualInvoice.getAmount());
        Assert.assertNull(actualInvoice.getTvaIntraCom());

        Assert.assertEquals(INVOICE_FR.getTvaFr(), actualInvoice.getDynamicFields().get("tvaFr"));

        INVOICE_FR.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoice.getDynamicFields().get(key));
        });
    }

    @Test
    public void testTransformFrToFr() throws IOException {
        DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class);
        InvoiceFr actualInvoice = (InvoiceFr) serializer.transform(INVOICE_FR);


        Assert.assertEquals(INVOICE_FR.getId(), actualInvoice.getId());
        Assert.assertEquals(INVOICE_FR.getAmount(), actualInvoice.getAmount());
        Assert.assertEquals(INVOICE_FR.getTvaFr(), actualInvoice.getTvaFr());

        INVOICE_FR.getDynamicFields().forEach((key, value) -> {
            Assert.assertEquals(value, actualInvoice.getDynamicFields().get(key));
        });
    }
}
