## Generador META-INF/services

A service provider framework is a system in which multiple service providers implement a service,
and the system makes the implementations available to its clients, decoupling them from the implementations.

### Por que esto y no autservice

Este generador esta ligado a REACTOR, asi que espera funciones extra
que aporten a dicho proyecto, aunque claro tambien puede usarse como
substituto a @AutoService  


### USO

Simplemente anota tu clase con :

1) Anota tu clase

```java
@ServiceProvider(ExtensionFactory.class)
public class GenericExtensionFactory implements ExtensionFactory {
    .......
}
```

2 Añade el procesador a tu proyecto si usas kotlin usa kapt

```gradle
dependencies{
 annotationProcessor 'io.skerna.commons:skerna-autogen:1.0.0-SNAPSHOT'
}
```