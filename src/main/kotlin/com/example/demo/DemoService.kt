package com.example.demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class DemoService {
    @Retryable
    suspend fun foobar(data: NonEmptyString): Int =
        coroutineScope {
            async(Dispatchers.IO) {
                data.nonBlankString.length
            }.await()
        }
}
