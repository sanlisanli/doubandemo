package me.mikasa.doubandemo.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by mikasa on 2018/12/26.
 */
abstract class BaseFragment:Fragment() {
    protected var mRootView:View?=null
    protected var mContext:Context?=null
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mContext=context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRootView=inflater?.inflate(getLayoutResId(),container,false)
        return mRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initData()
    }

    abstract fun getLayoutResId():Int
    open fun initView(){}
    open fun initData(){}
}