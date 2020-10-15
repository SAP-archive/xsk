package com.sap.xsk.xscjob.ds.tronsformer;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.sap.xsk.xscjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xscjob.ds.model.XSKJobDefinition;

@Singleton
public class XSKJobToXSKJobDefinitionTransformer {
    @Inject
    private XSKCronToQuartzCronTransformer xscCronToQuartzCronTransformer;

    public XSKJobDefinition transform(XSKJobArtifact xscJobArtifact) throws ParseException {
        XSKJobDefinition xscJobDefinition = new XSKJobDefinition();
        String[] parseAction = xscJobArtifact.getAction().split("::");

        for (int i = 0; i < xscJobArtifact.getSchedules().size(); i++) {
            String xscJobDefinitionName = xscJobArtifact.getAction() + "-" + i;
            xscJobDefinition.setName(xscJobDefinitionName);
            xscJobDefinition.setParametersAsMap(xscJobArtifact.getSchedules().get(i).getParameter());
            xscJobDefinition.setDescription(xscJobArtifact.getDescription() + " " + xscJobArtifact.getSchedules().get(i).getDescription());
        }

        String filePath = parseAction[0];
        String functionName = parseAction[1];
        filePath = xscPathToDirigiblePath(filePath);
        xscJobDefinition.setModule(filePath);
        xscJobDefinition.setFunction(functionName);

        String xscCronExpression = xscJobArtifact.getSchedules().get(0).getXscron();
        String quartzCronExpression = xscCronToQuartzCronTransformer.transform(xscCronExpression);
        xscJobDefinition.setCronExpression(quartzCronExpression);

        return xscJobDefinition;
    }

    public String xscPathToDirigiblePath(String xscFilePath) {
		String[] splitXscFilePath = xscFilePath.split(":");
		List<String> splitPackage = Arrays.asList(splitXscFilePath[0].split("\\."));

        return String.join("/", splitPackage) + "/" + splitXscFilePath[1];
    }
}
