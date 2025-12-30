package co.kr.datau.detekt.rules.provider

import co.kr.datau.detekt.rules.BaseUrlRule
import co.kr.datau.detekt.rules.ConstValueRule
import io.gitlab.arturbosch.detekt.api.*

class ConstRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "test-rules"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                ConstValueRule(config),
                BaseUrlRule(config)
            )
        )
    }
}
