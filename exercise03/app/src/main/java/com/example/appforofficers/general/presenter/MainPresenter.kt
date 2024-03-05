package com.example.appforofficers.general.presenter

import com.example.appforofficers.general.view.IMainView


class MainPresenter(val view: IMainView): IMainPresenter {

    override fun onPageSelected(position: Int) {
        view.setTab(position)
    }

    override fun onTabSelected(position: Int) {
        view.setPage(position)
    }

}