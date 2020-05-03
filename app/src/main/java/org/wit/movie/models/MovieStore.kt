package org.wit.movie.models

interface MovieStore {
    fun findAll(): List<MovieModel>
    fun create(Movie: MovieModel)
    fun update(Movie: MovieModel)
    fun delete(Movie: MovieModel)
}