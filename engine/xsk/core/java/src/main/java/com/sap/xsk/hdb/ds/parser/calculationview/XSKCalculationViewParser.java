package com.sap.xsk.hdb.ds.parser.calculationview;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.dirigible.api.v3.security.UserFacade;

import com.sap.xsk.hdb.ds.api.IXSKDataStructureModel;
import com.sap.xsk.hdb.ds.api.XSKDataStructuresException;
import com.sap.xsk.hdb.ds.model.calculationview.XSKDataStructureCalculationViewModel;
import com.sap.xsk.hdb.ds.parser.XSKDataStructureParser;

public class XSKCalculationViewParser implements XSKDataStructureParser {
    @Override
    public XSKDataStructureCalculationViewModel parse(String location, String content) throws XSKDataStructuresException, IOException {
        XSKDataStructureCalculationViewModel calcviewModel = new XSKDataStructureCalculationViewModel();
        calcviewModel.setName(new File(location).getName());
        calcviewModel.setLocation(location);
        calcviewModel.setType(getType());
        calcviewModel.setHash(DigestUtils.md5Hex(content));
        calcviewModel.setCreatedBy(UserFacade.getName());
        calcviewModel.setCreatedAt(new Timestamp(new java.util.Date().getTime()));
        calcviewModel.setXml(content);
        return calcviewModel;
    }

    @Override
    public String getType() {
        return IXSKDataStructureModel.TYPE_CALCVIEW;
    }

    @Override
    public Class getDataStructureClass() {
        return XSKDataStructureCalculationViewModel.class;
    }
}
