package io.github.bluegroundltd.kfactory

/**
 * Basic [Factory] interface
 */
interface Factory<T : Any> {

  /**
   * Returns an object
   *
   * @return the produced object
   */
  fun produce(): T

  /**
   * Returns more than one object. Also allows for object modifications upon generation.
   *
   * @param tap modifies a produced object before returning it to the sequence generator
   * @return a Sequence of [produce]d objects
   */
  fun produceMany(tap: T.() -> Unit = { }): Sequence<T> = generateSequence { produce().apply(tap) }
}
