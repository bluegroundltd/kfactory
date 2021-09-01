package io.github.bluegroundltd.kfactory

/**
 * Modify a [TraitEnhancedFactory] with [FactoryTrait]s
 *
 * @param traits arbitrary number of [FactoryTrait]s
 * @return the same [TraitEnhancedFactory] for further enhancement
 */
fun <F : TraitEnhancedFactory> F.withTraits(vararg traits: FactoryTrait<F>): F =
  apply { traits.forEach { it.modifyWithTrait(this) } }

/**
 * Modify a [TraitEnhancedFactory] with a single [FactoryTrait]
 *
 * @param trait a [FactoryTrait] to be applied on a [TraitEnhancedFactory]
 * @return the same [TraitEnhancedFactory] for further enhancement
 */
fun <F : TraitEnhancedFactory> F.withTrait(trait: FactoryTrait<F>): F = this.withTraits(trait)
