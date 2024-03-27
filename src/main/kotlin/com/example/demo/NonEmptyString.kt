package com.example.demo

@JvmInline
value class NonEmptyString private constructor(val nonBlankString: String) {
    companion object {
        fun create(string: String) =
            if (string.isBlank())
                throw IllegalArgumentException("must not be blank")
            else
                NonEmptyString(string)

    }
}
