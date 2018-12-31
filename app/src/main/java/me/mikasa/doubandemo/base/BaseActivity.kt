package me.mikasa.doubandemo.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast

/**
 * Created by mikasa on 2018/12/26.
 */
abstract class BaseActivity:AppCompatActivity() {
    protected var mContext:Context?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        mContext=this
        initView()
        initData()
    }
    fun setToolBar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title=""
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->finish()
        }
        return true
    }
    fun showToast(msg:String){
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show()
    }

    abstract fun getLayoutResId():Int
    open fun initView(){}
    open fun initData(){}
}