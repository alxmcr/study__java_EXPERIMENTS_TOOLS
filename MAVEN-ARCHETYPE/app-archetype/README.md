# app-archetype

## RUN

```bash
mvn archetype:generate -DarchetypeGroupId=com.alxmcr -DarchetypeArtifactId=app-archetype -DarchetypeVersion=1.0 -DgroupId=com.union -Dversion=1.0 -DinteractiveMode=false -DarchetypeCatalog=local -DartifactId=app01
```

- *Name app:*

```bash
-DartifactId=app01
```

- *Disable* interact mode:

```bash
.. -DinteractiveMode=false
```

- Local

```bash
-DarchetypeCatalog=local
```