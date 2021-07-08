package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.min

data class NovoTopicoForm (

        @field:NotEmpty(message = "Titulo não pode ser em branco")
        @field:Size(min = 5, max = 100, message = "Titulo deve ter entre 5 e 100 letras")
        val titulo: String,

        @field:NotEmpty(message = "mensagem não pode ser em branco")
        val mensagem: String,

        @field:NotNull
        val idCurso: Long,

        @field:NotNull
        val idAutor: Long
    )