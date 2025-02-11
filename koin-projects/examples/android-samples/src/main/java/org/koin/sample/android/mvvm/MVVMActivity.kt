package org.koin.sample.android.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.mvvm_activity.*
import org.junit.Assert.assertEquals
import org.koin.android.ext.android.getKoin
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.getViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.sample.android.R
import org.koin.sample.android.components.ID
import org.koin.sample.android.components.mvvm.SimpleViewModel
import org.koin.sample.android.components.scope.Session
import org.koin.sample.android.scope.ScopedActivityA
import org.koin.sample.android.utils.navigateTo

class MVVMActivity : AppCompatActivity() {

    val simpleViewModel: SimpleViewModel by viewModel { parametersOf(ID) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        assertEquals(getViewModel<SimpleViewModel> { parametersOf(ID) }, simpleViewModel)

        title = "Android MVVM"
        setContentView(R.layout.mvvm_activity)

        supportFragmentManager.beginTransaction()
                .replace(R.id.mvvm_frame, MVVMFragment())
                .commit()

        getKoin().setProperty("session", currentScope.get<Session>())

        mvvm_button.setOnClickListener {
            navigateTo<ScopedActivityA>(isRoot = true)
        }
    }
}