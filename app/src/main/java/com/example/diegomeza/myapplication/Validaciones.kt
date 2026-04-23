package com.example.diegomeza.myapplication

import android.util.Patterns


fun validarEmail(email: String): Pair<Boolean,String>{
    return when{
        email.isEmpty() -> Pair(false,"El correo es requerido")
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Pair(false,"El correo es invalido")
        !email.endsWith("@test.com")-> Pair(false,"El email no es corporativo.")
        else -> Pair(true,"")
    }
}

fun validarPassword(password: String): Pair<Boolean,String>{
    return  when{
        password.isEmpty() -> Pair(false,"La contraseña es requerida")
        else -> Pair(true,"")
    }
}

fun validarNombre(nombre: String): Pair<Boolean,String>{
    return when{
            nombre.isEmpty() -> Pair(false,"El nombre es requerido")
            nombre.length < 3 -> Pair(false,"El nombre debe tener al menos 3 caracteres")
            else -> Pair(true,"")
    }
}

fun validarContraseña(contraseña: String, confirmarContraseña: String): Pair<Boolean,String>{
    return when{
        confirmarContraseña.isEmpty() -> Pair(false,"La contraseña es requerida")
        contraseña != confirmarContraseña -> Pair(false,"Las contraseñas no coinciden")
        else -> Pair(true,"")
    }
}