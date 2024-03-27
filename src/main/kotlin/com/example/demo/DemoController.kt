package com.example.demo

import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
    private val demoService: DemoService
) {
    @GetMapping("/api/demo")
    suspend fun demonstrateError(
        @RequestParam("string") string: String
    ): ResponseEntity<String> {
        val nes = try {
            NonEmptyString.create(string)
        } catch (e: IllegalArgumentException) {
            return ResponseEntity.badRequest().body("Not allowed")
        }

        val result = runBlocking {
            demoService.foobar(nes)
        }
        return ResponseEntity.ok("Length is $result")
    }
}
