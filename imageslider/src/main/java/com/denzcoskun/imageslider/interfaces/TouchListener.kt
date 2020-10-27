package com.denzcoskun.imageslider.interfaces

import com.denzcoskun.imageslider.constants.ActionTypes


/**
 * Created by Deniz Coşkun on 10/10/2020.
 * denzcoskun@hotmail.com
 * İstanbul
 */
interface TouchListener {
    /**
     * Click listener touched item function.
     *
     * @param  touched  slider boolean
     */
    fun onTouched(touched: ActionTypes)
}