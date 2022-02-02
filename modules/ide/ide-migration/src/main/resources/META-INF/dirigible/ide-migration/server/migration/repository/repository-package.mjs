export class RepositoryPackage {
    constructor(pkg) {
        this._pkg = pkg;
    }

    get packageName() {
        return this._pkg.package;
    }

    get packageFile() {
        let path = this.packageName.replace(/\./g, "/");

        return "/" + path;
    }
}
