export class ObjectTypeFilter {
    static filterObjects(includedTypes, excludedTypes, objectList) {
        let filteredObjects = objectList;

        if (includedTypes.length > 0) {
            filteredObjects = objectList.filter((object) => {
                return includedTypes.includes(object.suffix);
            });
        }

        if (excludedTypes.length > 0) {
            filteredObjects = filteredObjects.filter((object) => {
                return !excludedTypes.includes(object.suffix);
            });
        }

        return filteredObjects;
    }

    static typeIsIncluded(includedTypes, excludedTypes, type) {
        if (excludedTypes.includes(type)) {
            return false;
        }

        if (includedTypes.length > 0 && !includedTypes.includes(type)) {
            return false;
        }

        return true;
    }
}
