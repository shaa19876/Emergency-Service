package com.example.appforofficers.app

import android.content.Context
import com.example.appforofficers.general.presenter.MainPresenter
import com.example.appforofficers.incidents.presenter.IncidentsPresenter
import com.example.appforofficers.zones.presenter.ZoneDetailsPresenter
import com.example.appforofficers.zones.presenter.ZonesPresenter
import com.example.appforofficers.zones.view.ZonesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    val presenter: ZonesPresenter
    val presenterZone: ZoneDetailsPresenter
    val presenterIncidents: IncidentsPresenter
    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}