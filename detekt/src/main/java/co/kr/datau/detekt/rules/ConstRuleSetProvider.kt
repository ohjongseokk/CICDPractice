package co.kr.datau.detekt.rules

import io.gitlab.arturbosch.detekt.api.*

class ConstRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "const-rules"

    override fun instance(config: Config): RuleSet {
        return RuleSet(ruleSetId, listOf(ConstValueRule(config)))
    }
}
