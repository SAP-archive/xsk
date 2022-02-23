export class RepositoryObject {
    constructor(name, packageName, suffix) {
        this._name = name;
        this._packageName = packageName;
        this._suffix = suffix;
        this._toBeCreated = [];
        this._writeContent = true;
        this._container = -1;
    }

    get Name() {
        if (this._name) return this._name + "." + this._suffix;
        else return "." + this._suffix;
    }

    get simpleName() {
        return this._name;
    }

    get suffix() {
        return this._suffix;
    }

    get PackageName() {
        var PackageName = {
            packageName: this._packageName,
            originalLanguage: this._originalLanguage,
        };

        return PackageName;
    }

    get RunLocation() {
        var dotsRegex = /\.(?=([^"\\]*(\\.|"([^"\\]*\\.)*[^"\\]*"))*[^"]*$)/g;
        var path = this._packageName.replace(dotsRegex, "/");
        return "/" + path + "/" + this.Name;
    }

    get TargetFileName() {
        var PACKE_QUOTE_REMOVE_PATTERN = /\/\"(.+)\"\//g;
        return this.RunLocation.replace(PACKE_QUOTE_REMOVE_PATTERN, "/$1/");
    }

    get packagePath() {
        var dotsRegex = /\.(?=([^"\\]*(\\.|"([^"\\]*\\.)*[^"\\]*"))*[^"]*$)/g;
        var path = this._packageName.replace(dotsRegex, "/");
        return path;
    }

    get content() {
        return this._content;
    }

    get fullName() {
        return this._packageName + "::" + this._name;
    }

    set content(content) {
        this._content = content;
    }

    set originalLanguage(originalLanguage) {
        this._originalLanguage = originalLanguage;
    }

    get privilegeObjectName() {
        return this._packageName + "/" + this._name;
    }

    get packageId() {
        return this._packageName;
    }

    set toBeCreated(toBeCreated) {
        this._toBeCreated = this._toBeCreated.concat(toBeCreated);
    }

    get toBeCreated() {
        return this._toBeCreated;
    }

    doNotWriteContent() {
        this._writeContent = false;
    }

    writeContent() {
        return this._writeContent;
    }

    set container(containerId) {
        this._container = containerId;
    }

    set suffix(suffix) {
        this._suffix = suffix;
    }

    get container() {
        return this._container;
    }
}
