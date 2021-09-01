<p align="center">
  <img width="240" height="240" src="https://raw.githubusercontent.com/bluegroundltd/kfactory/master/logo.png" />
</p>

# KFactory

[![Build](https://github.com/bluegroundltd/kfactory/actions/workflows/ci_test.yaml/badge.svg?branch=main)](https://github.com/bluegroundltd/kfactory/actions/workflows/ci_test.yaml)

Create best-in-class factories for your synthetic data in Kotlin.

⭐ &nbsp; Test fixtures<br/>
⭐ &nbsp; DB seeding<br/>
⭐ &nbsp; Feature demos<br/>
⭐ &nbsp; Pre-production environments<br/>


<hr/>

- [About KFactory](#about-kfactory)
- [Installation](#installation)
- [Usage](#usage)
  - [Creating a Factory](#creating-a-new-factory)
  - [Introducing Traits](#introducing-factory-traits)
  - [Enhancing a Factory](#enhancing-a-factory-with-traits)
  - [Producing objects](#producing-objects-from-a-factory)
  - [Dynamic values](#generating-dynamic-values-every-time)


## About KFactory

### Why synthetic data?

Chances are that synthetic data are going to be a major painpoint
for your project sooner than later.

* Local development
* Unit tests
* Exploratory testing
* Showcase a feature
* Pre-production environments

_Are you going to need synthetic data for any of the above?_

`Yes!` - Probably for all of them

### Synthetic data strategies

There are 3 major ways to create synthetic data that we are aware of:

1. Static fixtures
2. Factories
3. Production copies

💙 &nbsp;**Static fixtures** usually live in plain YAML or JSON files.
They are typically fed directly to the underlying database, skipping standard
validation/integrity checks for simplicity and performance. Since data integrity
is not a priority certain things like exploratory testing and refactorings become much harder.
Finally, due to their hardcoded nature, generating large volumes of data is out of question.
That's why we typically recommend static fixtures only for small apps.

💚 &nbsp;**Production copies** provide a great way to populate a system with data.
You typically get valid, large and versatile enough data to cover most of use cases.
BUT if you think about it... usually production data contains sensitive data and you'll need some
sort of obfuscation/anonymization before you load them into a develoment/test system.
That's :exploding_head: - especially when there are multiple databases involved.

Some other times, **production copies** can be too small - during the early days of a product - or too
large to be of practical use. And certainly, you cannot really write unit or functional tests
on production copies cause they are dynamic and unpredictable. We typically recommend production
copies for populating pre-production environments.

❤️ &nbsp;**Factories** or dynamic fixtures if you like, directly produce models
from within our domain. Models that can be validated and stored in the
underlying database with all integrity checks in place. A well made
set of factories, is one that captures all the necessary abstractions that
let you create data for a certain business scenario in a few lines of code.
We typically recommend factories for most use cases, but primarily for
creating test fixtures and populating local development environments.


### Why KFactory?

Other ecosystems have robust synthetic data solutions for some time now,
mostly inspired from Ruby's amazing [FactoryBot](https://github.com/thoughtbot/factory_bot).

KFactory is also inspired by FactoryBot - in a Kotlin idiomatic way.

* Built-in helpers
* Composable factories
* Traits
* Lazy sequence builds

<br/>

## Installation

KFactory is published on `mavenCentral`. In order to use it just add the following dependency:

```gradle
implementation("io.github.bluegroundltd:kfactory:1.0.0")
```

## Usage

You can find a lot of factory examples inside [examples](examples) directory!

## API Reference

API reference is available under this [link](https://bluegroundltd.github.io/kfactory/)!

### Creating a new Factory

When you have a domain entity that you want to create a `Factory` for you can start
by doing the following:

```kotlin
class AddressFactory : Factory<Address> {
  override fun produce() : Address = Address()
}
```

### Introducing Factory traits

A lot of times we want to produce fixtures from factories, but we only need to change only
a few of their attributes/characteristics.

For example:
```kotlin
class AddressFactory(
  private var city: String = "city",
  private var state: String = "state",
) : Factory<Address> {

  fun withCity(city: String) = apply {
    this.city = city
  }

  fun withState(state: String) = apply {
    this.state = state
  }

  override fun produce() : Address = Address(
    city = city,
    state = state
  )
}
```

Now if we want to produce several instances of `Address` that will retain `city`
but will have another specific value for `state`, we can create a new `FactoryTrait`
that we will later apply to that `Factory`.

```kotlin
object CaliforniaTrait : FactoryTrait<AddressFactory> {
  override fun modifyWithTrait(factory: AddressFactory): AddressFactory = factory
    .withState(state = "California")
}
```

### Enhancing a Factory with Traits

In order to enhance our `Factory` with a `FactoryTrait` like we previously saw,
we need to use the `TraitEnhancedFactory` marker interface.

For example consider the following:

```kotlin
class AddressFactory(
  private var city: String = "city",
  private var state: String = "state",
) : Factory<Address>, TraitEnhancedFactory {

  fun withCity(city: String) = apply {
    this.city = city
  }

  fun withState(state: String) = apply {
    this.state = state
  }

  override fun produce() : Address = Address(
    city = city,
    state = state
  )
}
```

This immediately adds two new extension functions on our `Factory`:
```kotlin
fun withTraits(vararg traits: FactoryTrait)

fun withTrait(trait: FactoryTrait)
```

We can now start building factories with distinctive characteristics:
```kotlin
val californiaFactory: AddressFactory = AddressFactory()
  .withTraits(CaliforniaTrait)

```

### Producing objects from a Factory

As described above we utilize factories in order to produce fixture data.

This can be done by invoking the following function on a `Factory`:

```kotlin
val address: Address = californiaFactory.produce()
```

If we need to generate more than on instance of our fixture data, we can utilize the
following function of a `Factory` that returns a `Sequence` of objects:

```kotlin
val addresses: List<Address> = californiaFactory.produceMany()
  .take(5)
  .toList()
```

### Generating dynamic values every time

Most of the time in our fixture data we might need to produce random values, or have
a new value generated every time we invoke `.produce()` on one of our factories.

For that purpose, we include a `typealias` in our library, named `Yielded` and our
proposed usage is the following:

```kotlin
class AddressFactory(
  private var city: String = "city",
  private var state: String = "state",
  private var streetNum: Yielded<Int> = { Random.nextint(1,5) }
) : Factory<Address>, TraitEnhancedFactory {

  fun withCity(city: String) = apply {
    this.city = city
  }

  fun withState(state: String) = apply {
    this.state = state
  }

  fun withStreetNum(streetNum: Int) = apply {
    this.streetNum = { streetNum }
  }

  fun withStreetNum(streetNum: Yielded<Int>) = apply {
    this.streetNum = streetNum
  }

  override fun produce() : Address = Address(
    city = city,
    state = state,
    streetNum = streetNum()
  )
}
```

From the above example, we can see that we have two new functions in our `Factory`.

These functions allow us to override the value generated for `streetNum` to have either
a static value every time we invoke `.produce()`, or a dynamic one. By default, the value
of it will be a lambda function which delegates to `Random.nextInt()` each time.

### Publishing

* Bump version in `gradle.properties` of `kfactory` module.
* Execute the following to upload artifact:
```shell
$ ./gradlew :kfactory:publish \
            --no-daemon --no-parallel \
            -Psigning.secretKeyRingFile=<keyring_file_path> \
            -Psigning.password=<keyring_password> \
            -Psigning.keyId=<keyring_id> \
            -PmavenCentralUsername=<nexus_username> \ 
            -PmavenCentralPassword=<nexus_password>
```

After this operation finishes, you can promote the artifact to be releasable with:
```shell
$ ./gradlew closeAndReleaseRepository \
            -PmavenCentralUsername=<nexus_username> \
            -PmavenCentralPassword=<nexus_password>
```

## Maintainers

The core maintainer of this project, is the Platform Team of [Blueground](https://theblueground.com)!

* [Stratos Pavlakis](https://github.com/th3hunt)
* [Andreas Gounaris](https://github.com/andrikoz)
* [Panagiotis Papadopoulos](https://github.com/panagpapad)
* [Pavlos-Petros Tournaris](https://github.com/pavlospt)
