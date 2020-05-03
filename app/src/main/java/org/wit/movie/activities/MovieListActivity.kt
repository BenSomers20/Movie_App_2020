package org.wit.movie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_movie_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.movie.R
import org.wit.movie.main.MainApp
import org.wit.movie.models.MovieModel

class MovieListActivity : AppCompatActivity(), MovieListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)
        app = application as MainApp

        val layoutManager =
            LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MovieAdapter(app.movies.findAll(), this)

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<MovieActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMovieClick(movie: MovieModel) {
        startActivityForResult(intentFor<MovieActivity>().putExtra("movie_edit", movie), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadMovies()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadMovies() {
        showMovies(app.movies.findAll())
    }

    fun showMovies (movies: List<MovieModel>) {
        recyclerView.adapter = MovieAdapter(movies, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}