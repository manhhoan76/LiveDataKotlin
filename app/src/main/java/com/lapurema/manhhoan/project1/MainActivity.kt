package com.lapurema.manhhoan.project1

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener    {

    private var content: FrameLayout? = null
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val saleFragment = SaleFragment()
    private val notificationFragment = NotificationFragment()
    var currentFragment: Fragment = homeFragment
    var currentMenuItem = 0
    var typeIcon = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.menu.getItem(0).isChecked = true
        setSupportActionBar(toolbar)
        imageToolbar.setImageResource(R.drawable.rsz_logo_white)

        val actionbar: ActionBar? = supportActionBar
        actionbar!!.apply {
            setDisplayHomeAsUpEnabled(false)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        imageToolbarDrawer.setOnClickListener {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        //Khởi động icon mặc định của action bar để mở drawer
        //toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.navigation)
        content = findViewById<FrameLayout>(R.id.content)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerBottom)
        if (savedInstanceState == null) {
            addFragment(homeFragment)
        }

    }

    fun showMessage(type: Int) {
        typeIcon = type
        if (typeIcon == 1) {
            imageToolbarDrawer.setImageResource(R.mipmap.ic_arrow_back_white_36dp)
            imageToolbarDrawer.setOnClickListener {
                onBackPressed()
            }
        } else {
            imageToolbarDrawer.setImageResource(R.mipmap.top_bar_menu)
            imageToolbarDrawer.setOnClickListener {
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }
    }


    private val mOnNavigationItemSelectedListenerBottom =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(homeFragment)
                    nav_view.menu.getItem(0).isChecked = true
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_search -> {
                    replaceFragment(searchFragment)
                    nav_view.menu.getItem(1).isChecked = true
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_sale -> {
                    replaceFragment(saleFragment)
                    nav_view.menu.getItem(2).isChecked = true
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    replaceFragment(notificationFragment)
                    nav_view.menu.getItem(3).isChecked = true
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun addFragment(fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction().add(R.id.content, fragment)
            .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        if (currentFragment == fragment) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .commit()
        } else {
            currentFragment = fragment
            supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.simpleName)
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .addToBackStack(fragment.javaClass.simpleName)
                .commit()
        }
    }

    fun switchIconNavigation(index: Int) {
        when (index) {
            1 -> {
                nav_view.menu.getItem(0).isChecked = true
                navigation.selectedItemId = R.id.navigation_home
            }
            2 -> {
                navigation.selectedItemId = R.id.navigation_search
                nav_view.menu.getItem(1).isChecked = true
            }
            3 -> {
                navigation.selectedItemId = R.id.navigation_sale
                nav_view.menu.getItem(2).isChecked = true
            }
            4 -> {
                navigation.selectedItemId = R.id.navigation_notifications
                nav_view.menu.getItem(3).isChecked = true
            }

        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
                super.onBackPressed()
            } else
                super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (item.itemId == 0) {
            drawer_layout.closeDrawer(GravityCompat.START)
            return false
        }
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(applicationContext, "AAA", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var selectedFragment = Fragment()
        when (item.itemId) {
            R.id.navigation_home -> {
                navigation.selectedItemId = R.id.navigation_home
                selectedFragment = homeFragment

            }
            R.id.navigation_search -> {
                navigation.selectedItemId = R.id.navigation_search
                selectedFragment = searchFragment
            }
            R.id.navigation_sale -> {
                navigation.selectedItemId = R.id.navigation_sale
                selectedFragment = saleFragment
            }
            R.id.navigation_notifications -> {
                navigation.selectedItemId = R.id.navigation_notifications
                selectedFragment = notificationFragment
            }
        }
        currentMenuItem = item.itemId
        replaceFragment(selectedFragment)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
