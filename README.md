# A Tiny Spring Framework

This is an example of a tiny Spring Framework that I built to understand the basic concepts of the Spring Framework. 
It is a simple implementation of the Dependency Injection (DI) and Aspect-Oriented Programming (AOP) concepts. 
The projecte is composed of the framework itself and a simple example of a REST API that uses the framework.

## The Application
The entry point of the application is the `MyExampleApplication` class situated at the root package `cat.tecnocampus` while the rest
of the application is in the `cat.tecnocampus.application` package.

#### Components
The application is a @RestController (MyController) that uses a @Service (MyService) that in turn uses a @Component (MyOtherService).

#### Dependency Injection
In the former sentence, uses means that the MyController has a dependency on MyService, and MyService has a dependency on MyOtherService and
all are injected by the framework. 

#### Application Context
Before injecting the dependencies, the framework creates an instance for each class and 
registers them in the application context.

#### Aspect-Oriented Programming (AOP)
MyOtherService is annotated with @Validated and implements an interface (MyOtherServiceInterface) where the method `doSomething` is defined
and its parameter is annotated with @Length. The framework uses AOP to intercept the call to the method `doSomething` and check if the parameter
length is between a given number of characters interval.

## The Framework
Is composed of three main packages. Namely, the `core`, `validationAOP` and `webModule`.

### Core
#### Annotations
#### Component Scan
#### Dependency Injection

### ValidationAOP


### WebModule


## Bibliography 
### Other Tiny Spring Frameworks
I got inspiration from the following articles:
* https://akoserwal.medium.com/building-a-minimal-spring-framework-from-scratch-8faa73067cbc
* https://liangliangliangtan.github.io/2019-06-26-mini-spring-mvc.html

### AOP and dynamic proxy
* https://naveen-metta.medium.com/exploring-the-depths-of-dynamic-proxy-in-java-a-comprehensive-guide-f34fb45b38a3