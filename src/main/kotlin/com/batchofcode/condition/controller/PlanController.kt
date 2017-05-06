package com.batchofcode.condition.controller

import com.batchofcode.condition.model.TestPlan
import com.batchofcode.condition.model.TestRule
import com.batchofcode.condition.service.TestPlanService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = "/api/plan")
class PlanController(val testPlanService: TestPlanService) {
    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun getPlans(): ResponseEntity<String> {
        return ResponseEntity(ObjectMapper().writeValueAsString(testPlanService.getAll()), HttpStatus.OK)
    }

    @RequestMapping(value = "/{testPlanId}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json"))
    fun getPlan(@PathVariable testPlanId: String): ResponseEntity<String> {
        return ResponseEntity(ObjectMapper().writeValueAsString(testPlanService.getOne(testPlanId)), HttpStatus.OK)
    }

    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE), produces = arrayOf("application/json"))
    fun addPlan(@RequestBody newPlan: TestPlan): ResponseEntity<String> {
        val newPlan = testPlanService.save(newPlan)
        return ResponseEntity(ObjectMapper().writeValueAsString(newPlan), HttpStatus.OK)
    }

    @RequestMapping(value = "/{planId}/newRule", method = arrayOf(RequestMethod.POST), produces = arrayOf("application/json"))
    fun addRule(@PathVariable planId: String, @RequestBody newRule: TestRule): ResponseEntity<String> {
        val modifiedPlan = testPlanService.addRule(planId, newRule)
        return ResponseEntity(ObjectMapper().writeValueAsString(modifiedPlan), HttpStatus.OK)
    }
}