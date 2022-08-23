package com.dekeyserjarno.hoefsmidapp.objects.enums;

public enum InvoiceOption {

    logo, invoicenumber, expirationdate,

    //item
    unitprice,amount,
    totalitemexcl,totalitem,
    item,vta,english,

    // btw totals

    total0,total6,total12,total21,
    total0incl,total6incl,total12incl,total21incl,
    total0vta,total6vta,total12vta,total21vta,
    totalvta,totalexclvta,total,

    // client

    targetname,targetstreet,targethousenumber
    ,targetcity,targetpostalcode,
    targetprovince,targetcountry,


    // Source
    sourcename,sourcestreet,sourcehousenumber,
    sourcepostalcode,sourcecity,sourceprovince,
    sourcecountry,sourcebenumber
}
