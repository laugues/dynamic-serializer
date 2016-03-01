# dynamic-serializer
##Example
## Beans 
```java
public class DynamicFields {

    private Map<String, Object> dynamicFields;

    @JsonAnySetter
    public void addDynamicField(String name, Object value ) {
        this.dynamicFields.put(name, value);
    }
}

public class InvoiceFr extends DynamicFields {

    private String id;
    private Double tvaFr;
    private Double amount;
    private Integer quantity;
}

public class InvoiceEu extends DynamicFields {

    private String id;
    private String tvaIntraCom;
    private Double amount;
}
```

### Deserialize
#### Same Structure in input and output 
Json input has "Invoice Fr" structure
```json
{
  "id" : "12415_1524545",
  "tvaFr" : 0.2,
  "amount" : 12548.58,
  "quantity" : 8,
  "dynamicFields" : {
    "supplierId" : "15254",
    "clientId" : "10001",
    "frRules" : [ "Rule 1", "Rule 2", "Rule 3" ]
  }
}
```
```java
DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class);
InvoiceFr invoiceFr = (InvoiceFr) serializer.deSerialize(inputJson);
```
InvoiceFr bean can be used 
```java
invoiceFr.getId();
invoiceFr.getQuantity();
```

#### Different Structure in input and output 
Json input has "Invoice Fr" structure
```json
{
  "id" : "12415_1524545",
  "tvaFr" : 0.2,
  "amount" : 12548.58,
  "quantity" : 8,
  "dynamicFields" : {
    "supplierId" : "15254",
    "clientId" : "10001",
    "frRules" : [ "Rule 1", "Rule 2", "Rule 3" ]
  }
}
```
```java
DynamicSerializer serializer = new DynamicSerializer(InvoiceEu.class);
InvoiceEu invoiceEu = (InvoiceEu) serializer.deSerialize(inputJson);
```
```java
invoiceEu.getId();
invoiceEu.getDynamicFields().get("quantity");
```
### Serialize
Bean in input is an InvoiceFr
```java
Map<String, Object> dynamicFields = new LinkedHashMap<String, Object>();
List<String> frRules = new ArrayList<String>();
frRules.add("Rule 1");
frRules.add("Rule 2");
frRules.add("Rule 3");
dynamicFields.put("frRules", frRules);
InvoiceFr invoiceFr = new InvoiceFr("12415_1524545", new Double(0.2), new Double(12548.58), 8, dynamicFields);
```
```java
DynamicSerializer serializer = new DynamicSerializer(InvoiceFr.class);
String actual = serializer.serialize(invoiceFr);
```
String output is 
```json
{
  "id" : "12415_1524545",
  "tvaFr" : 0.2,
  "amount" : 12548.58,
  "quantity" : 8,
  "dynamicFields" : {
    "supplierId" : "15254",
    "clientId" : "10001",
    "frRules" : [ "Rule 1", "Rule 2", "Rule 3" ]
  }
}
```
