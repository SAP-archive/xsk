## XSK Buildpack

1. Set the XSK version:
    > Replace the `#{XSKVersion}#` placeholder (e.g. `latest`, `0.7.1`, `1.0.0`) in `buildpack/*.toml` files.

1. Build `XSK Stack`:

    ```
    docker build -t dirigiblelabs/buildpacks-stack-base-xsk . --target base
    docker push dirigiblelabs/buildpacks-stack-base-xsk

    docker build -t dirigiblelabs/buildpacks-stack-run-xsk . --target run
    docker push dirigiblelabs/buildpacks-stack-run-xsk

    docker build -t dirigiblelabs/buildpacks-stack-build-xsk . --target build
    docker push dirigiblelabs/buildpacks-stack-build-xsk
    ```

1. Build `XSK Buildpack`:

    ```
    cd buildpack/

    pack buildpack package dirigiblelabs/buildpacks-xsk --config ./package.toml
    docker push dirigiblelabs/buildpacks-xsk

    pack builder create dirigiblelabs/buildpacks-builder-xsk --config ./builder.toml
    docker push dirigiblelabs/buildpacks-builder-xsk
    ```

1. Usage with `pack`:

    ```
    pack build --builder dirigiblelabs/buildpacks-builder-xsk <my-org>/<my-repository>
    ```