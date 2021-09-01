package io.github.bluegroundltd.kfactory

/**
 * Decorates a [TraitEnhancedFactory] with traits.
 */
interface FactoryTrait<F : TraitEnhancedFactory> {

  /**
   * Apply a [FactoryTrait] to a [TraitEnhancedFactory]
   *
   * @param factory a [TraitEnhancedFactory] for enhancement
   * @return the same [TraitEnhancedFactory] for further enhancement
   */
  fun modifyWithTrait(factory: F): F
}
