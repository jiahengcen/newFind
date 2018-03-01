package com.pwc.newfind.home

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.pwc.newfind.R
import com.pwc.newfind.setting.SettingsFragment
import com.pwc.newfind.finding.FindingFragment
import com.pwc.newfind.finding.NewFindingFragment
import com.pwc.newfind.industry.IndustryFragment
import com.pwc.newfind.report.ResearchFragment
import com.pwc.newfind.subscription.SubscriptionFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        firstGo(nav_view)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
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
        when (item.itemId) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun firstGo(navigationView: NavigationView) {
        navigationView.getMenu().getItem(0).setChecked(true);
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.content_view, SubscriptionFragment(), "Subscription");
        ft.commitAllowingStateLoss()
        toolbar.title = "订阅"
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_favourite -> {

                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view, SubscriptionFragment(), "Subscription");
                ft.commitAllowingStateLoss()
                toolbar.title = "订阅"
            }
            R.id.nav_finding -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view, NewFindingFragment(), "finding");
                ft.commitAllowingStateLoss()
                toolbar.title = "发现"
            }
            R.id.nav_slideshow -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view, ResearchFragment(), "research");
                ft.commitAllowingStateLoss()
                toolbar.title = "报告"
            }
            R.id.nav_industry -> {
                toolbar.title = "行业"
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view, IndustryFragment(), "Industry")
                ft.commitAllowingStateLoss()
            }
            R.id.nav_setting -> {
                val ft = supportFragmentManager.beginTransaction()
                ft.replace(R.id.content_view, SettingsFragment(), "Settings");
                ft.commitAllowingStateLoss()
                toolbar.title = "个人设置"
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
