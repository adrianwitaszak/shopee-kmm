package com.shoppe.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.shoppe.models.User
import com.shoppe.models.UserInput
import com.shoppe.services.AuthService

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