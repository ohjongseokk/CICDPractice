package co.kr.datau.detekt.rules

import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.*

class BaseUrlRule(config: Config) : Rule(config) {
    override val issue = Issue(
        id = "CheckBaseUrl",
        severity = Severity.CodeSmell,
        description = "BASE_URL is contain dev",
        debt = Debt.FIVE_MINS
    )

    override fun visitProperty(property: KtProperty) {
        super.visitProperty(property)

        if (
            property.name == "BASE_URL" &&
            !property.isTopLevel &&
            property.containingKtFile.packageFqName.asString() == "co.kr.datau.cicdpractice"
        ) {
            val initializer = property.initializer?.text

            if (initializer?.contains("dev") == true) {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(property),
                        "BaseUrl must not contain dev, but found $initializer"
                    )
                )
            }
        }
    }
}
