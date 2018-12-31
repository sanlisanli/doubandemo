package me.mikasa.doubandemo.activity

import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.mikasa.doubandemo.R
import me.mikasa.doubandemo.base.BaseActivity
import me.mikasa.doubandemo.constant.Constants
import me.mikasa.doubandemo.fragment.CategoryFragment

class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener{
    private var theaterMovie:CategoryFragment?=null
    private var comingMovie:CategoryFragment?=null
    private var topMovie:CategoryFragment?=null
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSupportActionBar(toolbar_main)
        supportActionBar?.title=""
        initListener()
        //nav_main.setCheckedItem(R.id.movie_coming)
        initFragment()
    }

    override fun initData() {
    }
    private fun initListener(){
        nav_main.setNavigationItemSelectedListener(this)//setNavigationItemSelectedListener()
        toggle_main.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                drawer_main.openDrawer(GravityCompat.START)
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val tf=supportFragmentManager.beginTransaction()
        hideAllFragments(tf)
        when(item.itemId){
            R.id.movie_theater->{
                title_main.text="豆瓣热映"
                if (theaterMovie==null){
                    theaterMovie=CategoryFragment.newInstance(Constants.THEATER_MOVIE)
                    tf.add(R.id.main_container,theaterMovie)
                }else{
                    tf.show(theaterMovie)
                }
            }
            R.id.movie_coming->{
                title_main.text="豆瓣推荐"
                if (comingMovie==null){
                    comingMovie=CategoryFragment.newInstance(Constants.COMING_MOVIE)
                    tf.add(R.id.main_container,comingMovie)
                }else{
                    tf.show(comingMovie)
                }
            }
            R.id.movie_top->{
                title_main.text="豆瓣经典"
                if (topMovie==null){
                    topMovie=CategoryFragment.newInstance(Constants.TOP_MOVIE)
                    tf.add(R.id.main_container,topMovie)
                }else{
                    tf.show(topMovie)
                }
            }

        }
        tf.commit()
        drawer_main.closeDrawer(GravityCompat.START)
        return true
    }

    private fun initFragment(){
        title_main.text="豆瓣推荐"
        val manager=supportFragmentManager
        val tf=manager.beginTransaction()
        comingMovie= CategoryFragment.newInstance(Constants.COMING_MOVIE)
        tf.add(R.id.main_container,comingMovie)
        tf.commit()
    }
    private fun hideAllFragments(tf:FragmentTransaction){
        theaterMovie?.let { tf.hide(it) }
        comingMovie?.let { tf.hide(it) }
        topMovie?.let { tf.hide(it) }
    }
}
