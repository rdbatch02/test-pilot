package com.batchofcode.observe.model

import javax.persistence.*

/**
 * Created by ryanbatchelder on 2/19/17.
 */
@Table(name = "test_rule")
@Entity
data class TestRule (
        @Id
        @Column
        val id: String,
        @Column
        val eventName: String,
        @Column
        val count: Int,
        @Column
        var satisfied: Boolean = false,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "test_plan_id")
        val planId: TestPlan
)