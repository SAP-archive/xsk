package com.sap.xsk.hdb.ds.parser;

import java.io.IOException;

import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;

public interface XSKDataStructureParser {

    <T> T parse(String location, String content) throws XSKDataStructuresException, IOException;

    String getType();

    <T> Class<T> getDataStructureClass();
}
