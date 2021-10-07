---
title: HDBCDS
---

HDBCDS
===

## Overview
---

The information on this page will help you learn how to develop the design-time data-persistence model for an XSK application using the HDBCDS syntax, also known as HDBDD.

## CDS file
---

CDS documents are design-time source files that contain DDL code that describes a persistence model according to rules defined in Core Data Services.
CDS documents have the file suffix .hdbdd. Each CDS document must contain the following basic elements:

1. namespace

	The name space you define must be the first declaration in the CDS document and match the absolute package path to the location of the CDS document in the repository.

2. schema definition

	The schema you specify is used to store the catalog objects that are defined in the CDS document, for example: entities, structured types, and views. The objects are 		generated in the catalog when the CDS document is synchronized by the xsk runtime.

3. CDS artifact definitions

	The objects that make up your persistence model, for example: contexts, entities and structured types

Each CDS document must contain one top-level artifact, for example: a context, a type, an entity, or a view. The name of the top-level artifact in the CDS document must match the file name of the CDS document, without the suffix. For example, if the top-level artifact is a context named MyModel, the name of the CDS document must be MyModel.hdbdd.

## CDS artifact

<h3>Context</h3>

If you want to define multiple CDS artifacts within a single CDS document (for example, multiple types, structured types, and entities), the top-level artifact must be a __context__. A CDS document can contain multiple contexts and any number and type of artifacts. A context can also contain nested sub-contexts, each of which can also contain any number and type of artifacts.

<h4>Example</h4>

```json
namespace com.acme.myapp1;

@Schema: 'MySchema'

context MyContext {

    // Nested contexts

    context InnerCtx {

        Entity MyEntity {
            …
        };

        Type CtxType {
            a : Integer;
            b : String(59);
        };
    };
};
```
<h3>Entity</h3>

In the SAP HANA database, as in other relational databases, a CDS entity is a table with a set of data elements. Each element will correspond to a table column on synchronizing the **.hdbdd** file.

<h4>Example</h4>

```json
entity MyTable {
    key Author    : String(100);
    key BookTitle : String(100);
    ISBN      : Integer not null;
    Publisher : String(100);
    elem2     : String(20) default 'John Doe';      
    elem3     : String(20) default 'John Doe' null; 
};
```

<h4>Element</h4>

```json
name: String(20);
age: Integer;
address: hana.VARCHAR;
```

Each element(field) of the entity definition has to contain at least a name and a type.

You can expand the definition of an entity element beyond the element's name and type by using element modifiers. For example, you can specify if an entity element is the primary key or part of the primary key. The following entity element modifiers are available:

- **key**

Defines if the specified element is the primary key or part of the primary key for the specified entity.

- **null**

Defines if an entity element can (null) or cannot (not null) have the value NULL. If neither null nor not null is specified for the element, the default value null applies (except for the key element).

 - **default** <literal_value>
Defines the default value for an entity element in the event that no value is provided during an INSERT operation. The syntax for the literals is defined in the primitive data-type specification.

Example of more literals wich could be used as default values for their corresponding scalar type.
```json
entity WithDefaults
{
    key id :  Integer;
    field1 :  Integer       default -42;
    field2 :  Integer64     default 9223372036854775807;
    field3 :  Decimal(5, 3) default 12.345;
    field4 :  BinaryFloat   default 123.456e-1;
    field5 :  LocalDate     default date'2013-04-29';
    field6 :  LocalTime     default time'17:04:03';
    field7 :  UTCDateTime   default timestamp'2013-05-01 01:02:03';
    field8 :  UTCTimestamp  default timestamp'2013-05-01 01:02:03';
    field9 :  Binary(32)    default x'0102030405060708090a0b0c0d0e0[...]';
    field10 : String(10)    default 'foo';
};
```

<h3>Structured User-Defined Types</h3>

A structured type is a data type comprising a list of attributes, each of which has its own data type. The attributes of the structured type can be defined manually in the structured type itself and reused either by another structured type or an entity.

In a structured user-defined type, you can define original types (aNumber in the following example) or reference existing types defined elsewhere in the same type definition or another, separate type definition (MyString80). If you define multiple types in a single CDS document, for example, in a parent context, each structure-type definition must be separated by a semi-colon (;).

The type MyString80 is defined in the following CDS document:

```json
namespace Package1.Package2;
@Schema: 'MySchema'
type MyString80: String(80);
```

A using directive is required to resolve the reference to the data type specified in otherText : MyString80;, as illustrated in the following example:

```json
namespace Package1.Package2;
using Package1.Package2::MyString80;  //contains definition of MyString80
@Schema: 'MySchema'
type MyStruct
{  
    aNumber   : Integer;  
    someText  : String(80);  
    otherText : MyString80;  // defined in a separate type
};
```

<h4>Nested Structured Types</h4>
Since user-defined types can make use of other user-defined types, you can build nested structured types, as illustrated in the following example:

```json
namespace com.sap.uni;

@Schema: 'MYSCHEMA'
context ContextA {
    type Address
    {
        street : String(50);
        number: Integer;
        zipCode: Integer;
    };

    type Student
    {
        name: String(50)
        age: Integer;
        address: Address;
    };
};
```

For each structured type, a SAP HANA table type is generated, whose name is built by concatenating the following elements of the CDS document containing the structured-type definition and separating the elements by a dot delimiter (.):

- the name space 
- the names of all artifacts that enclose the type 
- the name of the type itself

The columns of the table type are built by flattening the elements of the type. Elements with structured types are mapped to one column per nested element, with the column names built by concatenating the element names and separating the names by dots ".". Taking the above example the following will be generated in the database:

```json
create type "com.sap.uni::ContextA.Address" as table (
    street : varchar(50);
    number: integer;
    zipCode: integer;
);

create type "com.sap.uni::ContextA.Student" as table (
    name: varchar(50);
    age: integer;
    address.street : varchar(50);
    address.number : integer;
    address.zipCode : integer;
);
```

The new SAP HANA table types are generated in the schema that is specified in the schema annotation of the respective top-level artifact in the CDS document containing the structured types.

Table types are only generated for direct structure definitions; in the following example, this would include: MyStruct, MyNestedStruct, and MyDeepNestedStruct. No table types are generated for derived types that are based on structured types; in the following example, the derived types include: MyS, MyOtherInt, MyOtherStruct.

```json
namespace Pack1."pack-age2"; 
@Schema: 'MySchema'
context MyModel
{
    type MyInteger  : Integer;
    type MyString80 : String(80);
    type MyDecimal  : Decimal(10,2);

    type MyStruct  
    {
        aNumber   : Integer;
        someText  : String(80);
        otherText : MyString80;  // defined in example above
    };

    type MyS           : MyStruct;
    type MyOtherInt    : type of MyStruct.aNumber;
    type MyOtherStruct : type of MyDeepNestedStruct.nested.nested;

    type MyNestedStruct
    {
        name   : MyString80;
        nested : MyS;
    };

    type MyDeepNestedStruct
    {
        text   : LargeString;
        nested : MyNestedStruct;
    };
};
```

<h4>type of</h4>

You can also define a type based on an existing type that is already defined in another user-defined structured type, for example, by using the 'type of' keyword, as illustrated in the following example:

```json
type MyOtherInt : type of Address.number;           // => Integer 
type MyString : type of Student.address.street;     // => String(50)
```

The following code example shows how to use the type of keyword to define an element using the definition specified in another user-defined data-type field. For example, field4 : type of field3; indicates that, like field3, field4 is a LocalDate data type.

```json
entity MyEntity1 {
    key id  : Integer;
    field1  : MyType3;
    field2  : String(24);
    field3  : LocalDate;
    field4  : type of field3;
    field5  : type of MyType1.field2;  
    field6  : type of InnerCtx.CtxType.b;  // context reference
};
```

<h2>Associations</h2>

Associations define relationships between entities. Associations are specified by adding an element to a source entity with an association type that points to a target entity, complemented by optional information defining cardinality and which keys to use. There are two kinds of associations available:

- Managed Associations
- Unmanaged Associations


<h3>Association Syntax</h3>

Association *cardinality*  to *targetEntity* *managed* / *unmanaged*

<h4>cardinality</h4>
When using an association to define a relationship between entities in a CDS; you use the cardinality to specify the type of relation, for example:
- one-to-one (to-one)
- one-to-many (to-n)
	
```json
namespace samples;
@Schema: 'MYSCHEMA'              // XS classic *only* 
context AssociationCardinality {
    entity Associations {
        // To-one associations
        assoc1 : Association[0..1]    to target; 
        assoc2 : Association          to target; 
        assoc3 : Association[1]       to target; 
        assoc4 : Association[1..1]    to target; // association has one target instance
        // To-many associations
        assoc5 : Association[0..*]    to target{id1};
        assoc6 : Association[]        to target{id1}; // as assoc4, [] is short for [0..*]
        assoc7 : Association[2..7]    to target{id1}; // any numbers are possible; user provides
        assoc8 : Association[1, 0..*] to target{id1}; // additional info. about source cardinality
    };

    // Required to make the example above work
    entity target {
        key id1 : Integer;
        key id2 : Integer;
    };
};
```
	

Association Cardinality Syntax Examples: 
	
Association | Cardinality | Explanation
------------ | ------------- | -------------
assoc1 | [0..1] | The association has no or one target instance
assoc2 | [0..1] | Like assoc1, this association has no or one target instance and uses the default [0..1]
assoc3 | [1] | 	Like assoc1, this association has no or one target instance; the default for min is 0
assoc4 | [1..1] | The association has one target instance. No validation process will occur, so not specifing a target instance is still valid.
assoc5 | [0..*] | The association has no, one, or multiple target instances
assoc6 | [] | 	Like assoc4, [] is short for [0..*] (the association has no, one, or multiple target instances)
assoc7 | [2..7] | Any numbers are possible; the user provides. No validation will occur and unlimited number of target instances will be possible.
assoc8 | [1, 0..*] | The association has no, one, or multiple target instances and includes additional information about the source cardinality


<h4>managed</h4>
In the relational model, associations are mapped to foreign-key relationships. For managed associations, the relation between source and target entity is defined by specifying a set of elements of the target entity that are used as a foreign key.

If no foreign keys are specified explicitly, the elements of the target entity’s designated primary key are used. Elements of the target entity that reside inside substructures can be addressed by means of the respective path. If the chosen elements do not form a unique key of the target entity, the association has cardinality to-many. The following examples show how to express foreign keys in an association.

```json
entity Person
{
    key id : Integer;
    // address1,2,3 are to-one associations
    address1 : Association to Address;
    address2 : Association to Address { id };
    address3 : Association[1] to Address { zipCode, street, country };

    // address4,5,6 are to-many associations
    address4 : Association[0..*] to Address { zipCode };
    address5 : Association[*] to Address { street.name };
    address6 : Association[*] to Address { street.name AS streetName, country.name AS countryName };
};
```

Association | Keys | Explanation
------------ | ------------- | -------------
address1 |  | 	No foreign keys are specified: the target entity's primary key (the element id) is used as foreign key. Currently not a supported feature.
address2 | { id }| Explicitly specifies the foreign key (the element id); this definition is identical to address1.
address3 | { zipCode, street, country } | The foreign key elements to be used for the association are explicitly specified, namely: zipcode and the structured elements street and country.
address4 | { zipCode } | Uses only zipcode as the foreign key. Since zipcode is not a unique key for entity Address, this association has cardinality “to-many”. (No validation present and "to-one" relation is till possible. It should throw error!)
address5 | 	{ street.name } | Uses the sub-element name of the structured element street as a foreign key. This is not a unique key and, as a result, address4 has cardinality “to-many”.
address6 | { street.name AS streetName, country.name AS countryName } | Currently not suported syntax. It will be included in future releases.

<h4>unmanaged</h4>

Unmanaged associations are based on existing elements of the source and target entity; no fields are generated. In the ON condition, only elements of the source or the target entity can be used; it is not possible to use other associations. The ON condition may contain only comparison between target and source via the "=" sign.

In the following example, the association inhabitants relates the element id of the source entity Room with the element officeId in the target entity Employee. The target element officeId is accessed through the name of the association itself.

```json
namespace samples;
@Schema: 'MYSCHEMA'              // XS classic *only*
context UnmanagedAssociations {
    entity Employee {
        key id : Integer;
        officeId : Integer;
    };

    entity Room {
        key id : Integer;
        inhabitants : Association[*] to Employee on inhabitants.officeId = id;
    };

    entity Thing {
        key id : Integer;
        parentId : Integer;
        parent : Association[1] to Thing on parent.id = parentId;
        children : Association[*] to Thing on children.parentId = id;
    };
};

```

The following example defines two related unmanaged associations:

- parent

The unmanaged association parent uses a cardinality of [1] to create a relation between the element parentId and the target element id. The target element id is accessed through the name of the association itself.

- children

The unmanaged association children creates a relation between the element id and the target element parentId. The target element parentId is accessed through the name of the association itself.

```json
entity Thing {
    key id   : Integer;
    parentId : Integer;
    parent   : Association[1] to Thing on parent.id = parentId;
    children : Association[*] to Thing on children.parentId = id;
    ...
};
```

## Naming conventions

Rules and restrictions apply to the names of CDS documents and the package in which the CDS document resides.

The rules that apply for naming CDS documents are the same as the rules for naming the packages in which the CDS document is located. When specifying the name of a package or a CDS document (or referencing the name of an existing CDS object, for example, within a CDS document), bear in mind the following rules:

- File suffix
  - .hdbdd, for example, MyModel.hdbdd.

 - Permitted characters
 - 
CDS object and package names can include the following characters:

  - Lower or upper case letters(aA-zZ), the underscore character(_) and dash(-)
  - Digits(0-9)


- Forbidden characters
   - You cannot use the dot (.) in the name of a CDS document.
   - You cannot use a digit (0-9) as the first character of the name of either a CDS document or a package, for example, 2CDSobjectname.hdbdd (XS classic) or acme.com.1package.hdbcds (XS advanced).
   - The CDS parser does not recognize either CDS document names or package names that consist exclusively of digits, for example, 1234.hdbdd (XS classic) or acme.com.999.hdbcds.

Note: Although it is possible to use quotation marks ("") to wrap a name that includes forbidden characters, as a general rule, it is recommended to follow the naming conventions for CDS documents specified here in order to avoid problems during activation in the repository.

## External Artifacts in CDS

You can define an artifact in one CDS document by referring to an artifact that is defined in another CDS document.

The CDS syntax enables you to define a CDS artifact in one document by basing it on an “external” artifact - an artifact that is defined in a separate CDS document. Each external artifact must be explicitly declared in the source CDS document with the 'using' keyword, which specifies the location of the external artifact, its name, and where appropriate its CDS context.

The using declarations must be located in the header of the CDS document between the namespace declaration and the beginning of the top-level artifact, for example, the context.

The external artifact can be either a single object (for example, a type, an entity, or a view) or a context. You can also include an optional alias in the using declaration, for example, ContextA.ContextA1 as ic. The alias (ic) can then be used in subsequent type definitions in the source CDS document.

```json
namespace Pack1.Distributed;

using Pack1.Distributed::ContextA.T1;
using Pack1.Distributed::ContextA.ContextAI as ic;

using Pack1.Distributed::ContextA.ContextAI.T3 as ict3; 
using Pack1.Distributed::ContextA.ContextAI.T3.a as a;  // error, is not an artifact

context ContextB { 
    type T10 {
        a : T1;               // Integer
        b : ic.T2;            // String(20)
        c : ic.T3;            // structured
        d : type of ic.T3.b;  // String(88)
        e : ict3;             // structured
        x : Pack1.Distributed::ContextA.T1;  // error, direct reference not allowed
    };

    context ContextBI {
        type T1 : String(7);  // hides the T1 coming from the first using declaration
        type T2 : T1;         // String(7)
    };
};
```

The CDS document ContextB.hdbdd shown above uses external artifacts (data types T1 and T3) that are defined in the “target” CDS document ContextA.hdbdd shown below. Two using declarations are present in the CDS document ContextB.hdbdd; one with no alias and one with an explictly specified alias (ic). The first using declaration introduces the scalar type Pack1.Distributed::ContextA.T1. The second using declaration introduces the context Pack1.Distributed::ContextA.ContextAI and makes it accessible by means of the explicitly specified alias ic.

If no explicit alias is specified, the last part of the fully qualified name is assumed as the alias, for example T1.


## CDS Primitive Data Types

In the Data Definition Language (DDL), primitive (or core) data types are the basic building blocks that you use to define entities or structure types with DDL.
When you are specifying a design-time table (entity) using the CDS syntax, you use data types such as String, Binary, or Integer to specify the type of content in the entity columns. Here are the supported types  supports the use of the following primitive data types:
- DDL data types
- Native SAP HANA data types

<h4>DDL data types</h4>
	
Name | Description | SQL Literal Syntax | SQL Name 
------------ | ------------- | ------------- | ------------- 
String (n) | 	Variable-length Unicode string with a specified maximum length of n=1-1333 characters (5000 for SAP HANA specific objects). Default = maximum length. String length (n) is mandatory. | 'text with “quote”' | VARCHAR 
LargeString | 	Variable length string of up to 2 GB (no comparison) | 'text with “quote”' | NCLOB 
Binary(n) | 	Variable length byte string with user-defined length limit of up to 4000 bytes. Binary length (n) is mandatory. | x'01Cafe', X'01Cafe' | VARBINARY 
LargeBinary | 	Variable length byte string of up to 2 GB (no comparison) | x'01Cafe', X'01Cafe' | BLOB
Integer | 		Respective container's standard signed integer. Signed 32 bit integers in 2's complement, -2**31 .. 2**31-1. Default=NULL | 13, -1234567 | INTEGER
Integer64 | 		Signed 64-bit integer with a value range of -2^63 to 2^63-1. Default=NULL. | 13, -1234567 | BIGINT 
Decimal( p, s ) | Decimal number with fixed precision (p) in range of 1 to 34 and fixed scale (s) in range of 0 to p. Values for precision and scale are mandatory. | 12.345, -9.8767 | DECIMAL( p, s ) 
DecimalFloat | Decimal floating-point number (IEEE 754-2008) with 34 mantissa digits; range is roughly ±1e-6143 through ±9.99e+6144 | 12.345, -9.876 | DECIMAL
BinaryFloat| Binary floating-point number (IEEE 754), 8 bytes (roughly 16 decimal digits precision); range is roughly ±2.2207e-308 through ±1.7977e+308 | 1.2, -3.4, 5.6e+7 | DOUBLE
LocalDate| Local date with values ranging from 0001-01-01 through 9999-12-31 | date'1234-12-31' | DATE
LocalTime| 	Time values (with seconds precision) and values ranging from 00:00:00 through 24:00:00 |time'23:59:59', time'12:15' | TIME
UTCDateTime| 	UTC date and time (with seconds precision) and values ranging from 0001-01-01 00:00:00 through 9999-12-31 23:59:59 |timestamp'2011-12-31 23:59:59' | SECONDDATE
UTCTimestamp| 	UTC date and time (with a precision of 0.1 microseconds) and values ranging from 0001-01-01 00:00:00 through 9999-12-31 23:59:59.9999999, and a special initial value |timestamp'2011-12-31 23:59:59.7654321' | TIMESTAMP
Boolean| Represents the concept of binary-valued logic |true, false | BOOLEAN
	
In CDS, the name of SAP HANA data types are prefixed with the word “hana”, for example, hana.ALPHANUM, or hana.SMALLINT, or hana.TINYINT.
<h4>Native SAP HANA data types</h4>
	
Data Type | Description | SQL Name 
------------ | ------------- | ------------- 
ALPHANUM | 	Variable-length character string with special comparison. | ALPHANUMERIC 
SMALLINT | 	Signed 16-bit integer | SMALLINT 
TINYINT | 	Unsigned 8-bit integer | TINYINT 
REAL | 	32-bit binary floating-point number | REAL 
SMALLDECIMAL | 	64-bit decimal floating-point number | SMALLDECIMAL 
VARCHAR | 	Variable-length ASCII character string with user-definable length limit n | VARCHAR 
CLOB | 	Large variable-length ASCII character string, no comparison | CLOB 
BINARY | 	Byte string of fixed length n | BINARY 


## Reference
---

!!! note "SAP Help Portal"

    For more information, see [Core Data Services (.hdbcds)](https://help.sap.com/viewer/4505d0bdaf4948449b7f7379d24d0f0d/2.0.03/en-US/36257a5f611540f9b2f8e13110ddf97a.html).

## Sample
---

```json
namespace products.db;

@Schema: 'DBADMIN'

context Products {

    entity  Orders{
        key Id               : String(32);
        CustomerName         : String(500);
        CustomerSurname      : String(500);
        Status               : String(100);
        CreatedAt            : UTCTimestamp;
        CreatedBy            : String(5000);
        Description          : String(100);
        Address              : String(5000);
        Phone                : String(200);
        Email                : String(300);
        Country              : association to Products.Country { Id };
        items                : Association[*] to Item on items.OrderId = Id;
    };
     
    entity Item {
        key ItemId          : String(32);
    	OrderId             : String(32);
        Name                : String(500);
        Type                : String(100);
        Price               : String(100);
        Currency            : String(100);
        Quantity            : String(100);
        Comment             : String(1000);
    };
    
     entity Country {
        key Id              : String(32);
        Name                : String(32);
    };
};
```



