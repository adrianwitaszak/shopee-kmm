package com.adwi.ktor.graphql

import com.adwi.ktor.models.User
import com.adwi.ktor.models.UserInput
import com.adwi.ktor.services.AuthService
import com.apurebase.kgraphql.schema.dsl.SchemaBuilder

fun SchemaBuilder.authSchema(authService: AuthService) {
    mutation("signIn") {
        description = "Authenticate an existing user"
        resolver { userInput: UserInput ->
            try {
                authService.signIn(userInput)
            } catch (e: Exception) {
                null
            }
        }
    }

    mutation("signUp") {
        description = "Authenticate a new user"
        resolver { userInput: UserInput ->
            try {
                authService.signUp(userInput)
            } catch (e: Exception) {
                null
            }
        }
    }

    type<User> {
        User::hashedPass.ignore()
    }
}