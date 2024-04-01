package com.jaegerapps.hansan

import App
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.retainedComponent
import com.jaegerapps.hansan.common.billing.ChooseProduct
import com.jaegerapps.hansan.di.AppModule
import java.lang.Character.UnicodeBlock

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalDecomposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val appModule = AppModule(sharedPreferences)
        val root = retainedComponent {
            RootComponent(it, appModule)
        }


        setContent {
            val chooseProduct = remember {
                ChooseProduct(this)
            }
            LaunchedEffect(key1 = true) {
                chooseProduct.billingSetup()
                chooseProduct.checkProducts()
            }
            val products by chooseProduct.purchases.collectAsStateWithLifecycle()
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 20.dp,
                        horizontal = 20.dp
                    )
            ) {
                Text(
                    text = "Products",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                ListItem(
                    headlineContent = {
                        Text(text = "10 Recipes")
                    },
                    leadingContent = {
                        Text(text = if (products.contains("ten_recipes")) "Purchased" else "Not Purchased")
                    },
                    modifier = Modifier.clickable {
                        chooseProduct.purchase("hansan_pro_version")
                    }
                )
                ListItem(
                    headlineContent = {
                        Text(text = "10 Pizza Recipes")
                    },
                    leadingContent = {
                        Text(text = if (products.contains("ten_pizza_recipes")) "Purchased" else "Not Purchased")
                    },
                    modifier = Modifier.clickable {
                        chooseProduct.purchase("ten_pizza_recipes")
                    }
                )
            }
            /*WindowCompat.setDecorFitsSystemWindows(window, false)
            App(
                darkTheme = isSystemInDarkTheme(),
                root = root
            )*/
        }
    }

}
