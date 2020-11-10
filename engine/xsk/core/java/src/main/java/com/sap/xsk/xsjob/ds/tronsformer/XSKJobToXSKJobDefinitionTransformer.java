package com.sap.xsk.xsjob.ds.tronsformer;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.sap.xsk.xsjob.ds.model.XSKJobArtifact;
import com.sap.xsk.xsjob.ds.model.XSKJobDefinition;

@Singleton
public class XSKJobToXSKJobDefinitionTransformer {
    @Inject
    private XSKCronToQuartzCronTransformer xskCronToQuartzCronTransformer;

    public XSKJobDefinition transform(XSKJobArtifact xskJobArtifact) throws ParseException {
        XSKJobDefinition xskJobDefinition = new XSKJobDefinition();
        String[] parseAction = xskJobArtifact.getAction().split("::");

        for (int i = 0; i < xskJobArtifact.getSchedules().size(); i++) {
            String xskJobDefinitionName = xskJobArtifact.getAction() + "-" + i;
            xskJobDefinition.setName(xskJobDefinitionName);
            xskJobDefinition.setParametersAsMap(xskJobArtifact.getSchedules().get(i).getParameter());
            xskJobDefinition.setDescription(xskJobArtifact.getDescription() + " " + xskJobArtifact.getSchedules().get(i).getDescription());
        }

        String filePath = parseAction[0];
        String functionName = parseAction[1];
        filePath = xskPathToDirigiblePath(filePath);
        xskJobDefinition.setModule(filePath);
        xskJobDefinition.setFunction(functionName);

        String xskCronExpression = xskJobArtifact.getSchedules().get(0).getXscron();
        String quartzCronExpression = xskCronToQuartzCronTransformer.transform(xskCronExpression);
        xskJobDefinition.setCronExpression(quartzCronExpression);

        return xskJobDefinition;
    }

    public String xskPathToDirigiblePath(String xskFilePath) {
		String[] splitXscFilePath = xskFilePath.split(":");
		List<String> splitPackage = Arrays.asList(splitXscFilePath[0].split("\\."));

        return String.join("/", splitPackage) + "/" + splitXscFilePath[1];
    }
}
