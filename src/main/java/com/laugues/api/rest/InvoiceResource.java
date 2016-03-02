package com.laugues.api.rest;

import com.laugues.models.eu.InvoiceEu;
import com.laugues.models.fr.InvoiceFr;
import com.laugues.serializer.DynamicSerializer;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lds on 02/03/2016.
 */
@Path("/invoice")
public class InvoiceResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public InvoiceEu validateInvoice(InvoiceFr invoiceFr){

        DynamicSerializer dynamicSerializer = new DynamicSerializer(InvoiceFr.class, InvoiceEu.class);
        InvoiceEu invoiceEu = (InvoiceEu) dynamicSerializer.transform(invoiceFr);
        return invoiceEu;
    }

}
