package com.example.testkuadran.module

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testkuadran.R
import com.example.testkuadran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    MainActivityContract {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        initBinding()
        initUI()
        initAction()
        initNavigation()
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initUI() {
        setSupportActionBar(binding.toolbar)
    }

    override fun initNavigation() {
        navigator = findNavController(
            R.id.nav_host_fragment_content_main
        )
        appBarConfiguration = AppBarConfiguration(
            navigator.graph
        )
        setupActionBarWithNavController(
            navigator,
            appBarConfiguration
        )
    }

    override fun initAction() {

    }

    override fun onCreateOptionsMenu(
        menu: Menu
    ): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(
            R.menu.menu_main,
            menu
        )
        return true
    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    override fun onOptionsItemSelected(
        item: MenuItem
    ): Boolean =
        when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(
            R.id.nav_host_fragment_content_main
        )

        return navController
            .navigateUp(
                appBarConfiguration
            )
                || super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) =
        when (v) {
            else -> {}
        }
}