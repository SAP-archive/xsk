## XSK Cloud Foundry Buildpack

1. Set the XSK version:
    > Replace the `#{XSKVersion}#` placeholder (e.g. `latest`, `0.7.1`, `1.0.0`) in `buildpack/*.toml` files.If you need jpda in buildpack change in `buildpack/bin/build` add jpda in `command = "/bin/sh /usr/local/tomcat/bin/catalina.sh jpda run`.

1. Build `XSK Cloud Foundry Stack`:

    ```
    docker build -t dirigiblelabs/buildpacks-stack-base-xsk-cf . --target base
    docker push dirigiblelabs/buildpacks-stack-base-xsk-cf

    docker build -t dirigiblelabs/buildpacks-stack-run-xsk-cf . --target run
    docker push dirigiblelabs/buildpacks-stack-run-xsk-cf

    docker build -t dirigiblelabs/buildpacks-stack-build-xsk-cf . --target build
    docker push dirigiblelabs/buildpacks-stack-build-xsk-cf
    ```

1. Build `XSK Cloud Foundry Buildpack`:

    ```
    cd buildpack/

    pack buildpack package dirigiblelabs/buildpacks-xsk-cf --config ./package.toml
    docker push dirigiblelabs/buildpacks-xsk-cf

    pack builder create dirigiblelabs/buildpacks-builder-xsk-cf --config ./builder.toml
    docker push dirigiblelabs/buildpacks-builder-xsk-cf
    ```

1. Usage with `pack`:

    ```
    pack build --builder dirigiblelabs/buildpacks-builder-xsk-cf <my-org>/<my-repository>
    ```
