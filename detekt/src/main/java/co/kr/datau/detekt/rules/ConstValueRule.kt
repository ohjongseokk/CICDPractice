package co.kr.datau.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.*

class ConstValueRule(config: Config) : Rule(config) {
    override val issue = Issue(
        id = "CheckReleaseValueInt",
        severity = Severity.CodeSmell,
        description = "RELEASE_VALUE_INT must be 0",
        debt = Debt.FIVE_MINS
    )

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)

        if (
            property.name == "RELEASE_VALUE_INT" &&
            property.isTopLevel &&
            property.containingKtFile.packageFqName.asString() == "co.kr.datau.cicdpractice"
        ) {
            val initializer = property.initializer?.text

            if (initializer != "0") {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(property),
                        "RELEASE_VALUE_INT must be 0, but found $initializer"
                    )
                )
            }
        }
    }
}
